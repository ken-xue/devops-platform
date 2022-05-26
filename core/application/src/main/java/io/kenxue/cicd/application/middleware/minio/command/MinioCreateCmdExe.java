package io.kenxue.cicd.application.middleware.minio.command;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;
import io.kenxue.cicd.application.common.socket.constant.Constant;
import io.kenxue.cicd.application.common.socket.message.WebSocketMessageHandler;
import io.kenxue.cicd.application.common.socket.message.WebSocketMessageHandlerI;
import io.kenxue.cicd.application.common.socket.service.WebSocketLoggerService;
import io.kenxue.cicd.application.machine.util.MachineUtil;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.minio.MinioCreateCmd;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.domain.domain.middleware.Minio;
import io.kenxue.cicd.domain.factory.middleware.MinioFactory;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import io.kenxue.cicd.domain.repository.middleware.MinioRepository;
import io.kenxue.cicd.sharedataboject.common.command.CommandConst;
import io.kenxue.cicd.sharedataboject.machine.enums.LsbReleaseEnum;
import io.kenxue.cicd.sharedataboject.pipeline.constant.NodeConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static io.kenxue.cicd.application.common.util.JschUtil.getSession;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Slf4j
@Component
@WebSocketMessageHandler("minio")
public class MinioCreateCmdExe implements WebSocketMessageHandlerI {

    public final String CLUSTER_INSTALL_RESPONSE_TIP = String.format("%s%s%s",CommandConst.ENTER,"[当前主机(%s)正在安装(%s)节点]",CommandConst.ENTER);

    @Resource
    private MinioRepository minioRepository;
    @Resource
    private MachineInfoRepository machineInfoRepository;
    @Resource
    private WebSocketLoggerService createLoggerSocketService;
    @Value("${minio.install.cmd}")
    private String kubernetesInstallCmd;

    public Response execute(String message,WebSocketSession webSocketSession) {

        MinioCreateCmd cmd = JSONObject.parseObject(message, MinioCreateCmd.class);
        String key = String.valueOf(webSocketSession.getAttributes().get(Constant.USER_UUID_KEY));
        cmd.setResponseKey(key);

        List<String> machineUuidList = cmd.getNodeList().stream().map(v -> v.getValue()).collect(Collectors.toList());
        log.info("创建minio集群参数：{}",cmd);
        //1.获取需要搭建的机器
        List<MachineInfo> machineList = machineInfoRepository.getByUuid(machineUuidList);
        //3.获取jsch连接实例安装
        StringBuilder logger = new StringBuilder();

        machineList.forEach(machineInfo -> {
           log.info("当前安装机器:{}",machineInfo);
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

                createLoggerSocketService.sendMessage(cmd.getResponseKey(),String.format(CLUSTER_INSTALL_RESPONSE_TIP,machineInfo.getIp(),"master").getBytes(StandardCharsets.UTF_8));

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
                        createLoggerSocketService.sendMessage(cmd.getResponseKey(),resp.getBytes(StandardCharsets.UTF_8));
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

        //4.保存记录
        Minio minio = MinioFactory.getMinio();
        minio.create(UserThreadContext.get());
        minioRepository.create(minio);

        return Response.success();
    }
}
