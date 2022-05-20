package io.kenxue.cicd.application.kubernetes.cluster;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.kenxue.cicd.application.common.websocket.WebSocketService;
import io.kenxue.cicd.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterCreateCmd;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.domain.factory.kubernetes.ClusterFactory;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import io.kenxue.cicd.sharedataboject.pipeline.constant.CommandConst;
import io.kenxue.cicd.sharedataboject.pipeline.constant.NodeConst;
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

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
@Slf4j
public class ClusterCreateCmdExe {

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
        JSch jsch = new JSch();
        masterMachineInfo.forEach(machineInfo -> {
           log.info("安装master节点:{}",machineInfo);
            Session session = null;
            ChannelShell channel = null;
            try {
                session = jsch.getSession(machineInfo.getAccessUsername(), machineInfo.getIp(), machineInfo.getPort());
                // 设置密码
                session.setPassword(machineInfo.getAccessPassword());
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                // 为Session对象设置properties
                session.setConfig(config);
                // 设置timeout时间
                session.setTimeout(50000);
                // 通过Session建立链接
                session.connect();
                channel = (ChannelShell) session.openChannel("shell");
                channel.connect();
                OutputStream outputStream = channel.getOutputStream();
                InputStream inputStream = channel.getInputStream();
                outputStream.write((kubernetesInstallCmd+CommandConst.ENTER).getBytes(StandardCharsets.UTF_8));
                outputStream.write(CommandConst.EXIT.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();

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
        });
        //4.保存集群记录
        Cluster cluster = ClusterFactory.getCluster();
        cluster.create(UserThreadContext.get());

        clusterRepository.create(cluster);

        return Response.success();
    }

    public void executeShell(){

    }
}
