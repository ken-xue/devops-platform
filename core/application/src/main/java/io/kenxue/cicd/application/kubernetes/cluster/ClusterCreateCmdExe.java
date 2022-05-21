package io.kenxue.cicd.application.kubernetes.cluster;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.kenxue.cicd.application.common.websocket.WebSocketService;
import io.kenxue.cicd.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.cicd.application.machine.util.MachineUtil;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterCreateCmd;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.domain.factory.kubernetes.ClusterFactory;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import io.kenxue.cicd.sharedataboject.common.command.CommandConst;
import io.kenxue.cicd.sharedataboject.machine.enums.LsbReleaseEnum;
import io.kenxue.cicd.sharedataboject.pipeline.constant.NodeConst;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

import static io.kenxue.cicd.application.common.util.JschUtil.getSession;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
@Slf4j
public class ClusterCreateCmdExe {

    public static final String CLUSTER_INSTALL_RESPONSE_TIP = "\r\n[当前主机(%s)正在安装(%s)节点]\r\n";

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;
    @Resource
    private MachineInfoRepository machineInfoRepository;
    @Resource
    @Qualifier("clusterCreateLoggerSocketServiceImpl")
    private WebSocketService webSocketService;
    @Value("${kubernetes.install.cmd}")
    private String kubernetesInstallCmd;

    public Response execute(ClusterCreateCmd cmd) {
        log.info("创建kubernetes集群参数：{}",cmd);
        //1.获取master机器
        List<MachineInfo> masterMachineInfo = machineInfoRepository.getByUuid(cmd.getMasterHostList());
        //2.获取salve机器
        List<MachineInfo> slaveMachineInfo = machineInfoRepository.getByUuid(cmd.getSlaveHostList());
        //3.获取jsch连接实例安装
        StringBuilder logger = new StringBuilder();
        masterMachineInfo.forEach(machineInfo -> {
           log.info("安装master节点:{}",machineInfo);
            Session session = null;
            ChannelShell channel = null;
            try {
                //获取机器发行版
                LsbReleaseEnum release = MachineUtil.getRelease(machineInfo);
                session = getSession(machineInfo);
                channel = (ChannelShell) session.openChannel("shell");
                channel.connect();
                OutputStream outputStream = channel.getOutputStream();
                InputStream inputStream = channel.getInputStream();
                String exeCmd = String.format(kubernetesInstallCmd,release.getName())+CommandConst.ENTER;
                outputStream.write((exeCmd).getBytes(StandardCharsets.UTF_8));
                outputStream.write(CommandConst.EXIT.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();

                webSocketService.sendMessage(cmd.getResponseKey(), String.format(CLUSTER_INSTALL_RESPONSE_TIP,machineInfo.getIp(),"master").getBytes(StandardCharsets.UTF_8));

                byte[] buffer = new byte[1024];
                while (true) {
                    while (inputStream.available() > 0) {
                        int read = inputStream.read(buffer, 0, 1024);
                        if (read < 0) {
                            break;
                        }
                        String resp = new String(buffer, 0, read);
                        if (resp.indexOf(NodeConst.MORE) >= 0) {
                            outputStream.write((NodeConst.BLANK).getBytes());
                            outputStream.flush();
                        }
                        log.debug(resp);
                        log.info("推送日志数据 key:{}");
                        logger.append(resp);
                        webSocketService.sendMessage(cmd.getResponseKey(), resp.getBytes(StandardCharsets.UTF_8));
                    }
                    if (channel.isClosed()) {
                        if (inputStream.available() > 0) {
                            continue;
                        }
                        log.info("exit-status: " + channel.getExitStatus());
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                channel.disconnect();
                session.disconnect();
            }
        });

        slaveMachineInfo.forEach(machineInfo -> {
            log.info("安装slave节点:{}",machineInfo);
            // TODO: 2022/5/19
            webSocketService.sendMessage(cmd.getResponseKey(), String.format(CLUSTER_INSTALL_RESPONSE_TIP,machineInfo.getIp(),"slave").getBytes(StandardCharsets.UTF_8));

        });
        //4.保存集群记录
        Cluster cluster = ClusterFactory.getCluster();
        cluster.create(UserThreadContext.get());

        clusterRepository.create(cluster);

        return Response.success();
    }
}
