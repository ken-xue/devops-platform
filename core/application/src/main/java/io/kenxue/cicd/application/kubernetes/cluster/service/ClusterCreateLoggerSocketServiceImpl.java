package io.kenxue.cicd.application.kubernetes.cluster.service;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.cicd.application.common.websocket.Constant;
import io.kenxue.cicd.application.common.websocket.WebSocket;
import io.kenxue.cicd.application.common.websocket.WebSocketService;
import io.kenxue.cicd.application.kubernetes.cluster.ClusterCreateCmdExe;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterCreateCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Slf4j
@Service
@WebSocket("create")
public class ClusterCreateLoggerSocketServiceImpl implements WebSocketService {

    //存放ssh连接信息的map
    private volatile Map<String, WebSocketSession> webSocketConnectionPool = new ConcurrentHashMap<>(2 << 4);

    @Resource
    private ClusterCreateCmdExe clusterCreateCmdExe;

    @Override
    public void initConnection(WebSocketSession session) {
        String key = String.valueOf(session.getAttributes().get(Constant.USER_UUID_KEY));
        webSocketConnectionPool.put(key, session);
    }

    @Override
    public void recvHandle(String message, WebSocketSession session) {
        log.info("接收到创建集群参数：{}",message);
        ClusterCreateCmd clusterCreateCmd = JSONObject.parseObject(message, ClusterCreateCmd.class);
        String key = String.valueOf(session.getAttributes().get(Constant.USER_UUID_KEY));
        clusterCreateCmd.setResponseKey(key);
        clusterCreateCmdExe.execute(clusterCreateCmd);
    }

    @Override
    public void sendMessage(String key, byte[] message) {
        WebSocketSession webSocketSession = webSocketConnectionPool.get(key);
        if (webSocketSession == null) {
            log.info("当前 key:{} socket 无连接实例,无需推送", key);
            return;
        }
        try {
            webSocketSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     *
     * @param session
     */
    @Override
    public void close(WebSocketSession session) {
        String key = String.valueOf(session.getAttributes().get(Constant.USER_UUID_KEY));
        close(key);
    }

    /**
     * 通过key移除全部连接
     *
     * @param key
     */
    @Override
    public void close(String key) {
        WebSocketSession session = webSocketConnectionPool.remove(key);
        try {
            if (session != null) {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
