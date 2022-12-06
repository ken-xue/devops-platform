package io.kenxue.devops.application.kubernetes.cluster.service;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.kenxue.devops.application.common.socket.constant.Constant;
import io.kenxue.devops.application.common.socket.handler.WebSocket;
import io.kenxue.devops.application.common.socket.service.WebSocketService;
import io.kenxue.devops.application.kubernetes.manager.ClusterCacheManager;
import io.kenxue.devops.application.machine.terminal.SSHConnectInfo;
import io.kenxue.devops.application.machine.terminal.WebSSHData;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterPodDTO;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.domain.repository.machine.MachineInfoRepository;
import io.kubernetes.client.Attach;
import io.kubernetes.client.Exec;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * attach service具体实现
 */
@Service
@Slf4j
@WebSocket("attach")
public class AttachWebSocketServiceImpl implements WebSocketService {

    //存放ssh连接信息的map
    private static Map<String, Object> sshMap = new ConcurrentHashMap<>();
    //线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();
    @Resource
    private ClusterCacheManager cacheManager;

    /**
     * @Description: 初始化连接
     * @Param: [session]
     * @return: void
     */
    @Override
    public void initConnection(WebSocketSession session) {
        //所需参数：clusterId,namespace,pod name
        String param = session.getUri().getQuery();
        String[] ps = param.split("&");
        HashMap<String, String> map = new HashMap<>();
        for (String s:ps){
            String[] split = s.split("=");
            if (split.length==2)
            map.put(split[0],split[1]);
        }
        ClusterPodDTO pod = ClusterPodDTO.builder().clusterId(Long.valueOf(map.get("clusterId")))
                .name(map.get("name"))
                .namespace(Optional.ofNullable(map.get("namespace")).orElse("default"))
                .build();
        executorService.execute(() -> {
            try {
                attach(pod);
            } catch (Exception e) {
                try {
                    session.sendMessage(new TextMessage("连接异常 "+e.getMessage()));
                } catch (IOException ioe) {
                    log.error("响应异常:{}",ioe.getMessage());
                }
                log.error("连接异常:{}", e.getMessage());
                close(session);
            }
        });
    }

    private void attach(ClusterPodDTO pod) throws IOException, ApiException {
        ApiClient apiClient = cacheManager.get(pod.getClusterId());
        Configuration.setDefaultApiClient(apiClient);
        Exec exec = new Exec(apiClient);

        Attach attach = new Attach(apiClient);
        final Attach.AttachResult result = attach.attach(pod.getNamespace(),pod.getName() , true);

    }

    /**
     * @Description: 处理客户端发送的数据
     * @Param: [buffer, session]
     * @return: void
     */
    @Override
    public void recvHandle(String buffer, WebSocketSession session) {
        String userId = String.valueOf(session.getAttributes().get(Constant.USER_UUID_KEY));
        String command = buffer;
        SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(userId);
        if (sshConnectInfo != null) {
            try {
                transToSSH(sshConnectInfo.getChannel(), command);
            } catch (IOException e) {
                log.error("服务器连接异常:{}", e.getMessage());
                close(session);
            }
        }
    }

    @Override
    public void sendMessage(String key, byte[] buffer) {
        SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(key);
        try {
            sshConnectInfo.getWebSocketSession().sendMessage(new TextMessage(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(WebSocketSession session) {
        String uuid = (String) session.getAttributes().get(Constant.USER_UUID_KEY);
        SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(uuid);
        if (sshConnectInfo != null) {
            //断开连接
            if (sshConnectInfo.getChannel() != null) sshConnectInfo.getChannel().disconnect();
            //map中移除
            sshMap.remove(uuid);
        }
    }

    @Override
    public void close(String key) {
        SSHConnectInfo sshConnectInfo = (SSHConnectInfo) sshMap.get(key);
        if (sshConnectInfo != null) {
            //断开连接
            if (sshConnectInfo.getChannel() != null) sshConnectInfo.getChannel().disconnect();
            //map中移除
            sshMap.remove(key);
        }
    }

    /**
     * @Description: 使用jsch连接终端
     * @Param: [cloudSSH, webSSHData, webSocketSession]
     * @return: void
     */
    private void connectToSSH(SSHConnectInfo sshConnectInfo, WebSSHData webSSHData, WebSocketSession webSocketSession) throws JSchException, IOException {
        Session session = null;
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //获取jsch的会话
        session = sshConnectInfo.getJSch().getSession(webSSHData.getUsername(), webSSHData.getHost(), webSSHData.getPort());
        session.setConfig(config);
        //设置密码
        session.setPassword(webSSHData.getPassword());
        //连接  超时时间30s
        session.connect(30000);

        //开启shell通道
        Channel channel = session.openChannel("shell");

        //通道连接 超时时间3s
        channel.connect(3000);

        //设置channel
        sshConnectInfo.setChannel(channel);

        //转发消息
        transToSSH(channel, "\r");

        //读取终端返回的信息流
        InputStream inputStream = channel.getInputStream();
        try {
            //循环读取
            byte[] buffer = new byte[1024];
            int i = 0;
            //如果没有数据来，线程会一直阻塞在这个地方等待数据。
            while ((i = inputStream.read(buffer)) != -1) {
                sendMessage((String) webSocketSession.getAttributes().get(Constant.USER_UUID_KEY), Arrays.copyOfRange(buffer, 0, i));
            }

        } finally {
            //断开连接后关闭会话
            session.disconnect();
            channel.disconnect();
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    /**
     * @Description: 将消息转发到终端
     * @Param: [channel, data]
     * @return: void
     */
    private void transToSSH(Channel channel, String command) throws IOException {
        if (channel != null) {
            OutputStream outputStream = channel.getOutputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
        }
    }
}
