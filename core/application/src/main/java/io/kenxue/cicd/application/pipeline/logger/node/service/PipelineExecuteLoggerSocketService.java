package io.kenxue.cicd.application.pipeline.logger.node.service;

import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PushNodeExecuteStatusDTO;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Description: 流水节点执行日志传输服务
 */
public interface PipelineExecuteLoggerSocketService {
    /**
     * @Description: 初始化ssh连接
     */
    void initConnection(WebSocketSession session);

    /**
     * @Description: 处理客户段发的数据
     */
    void recvHandle(String buffer, WebSocketSession session);

    /**
     * @Description: 数据写回前端 for websocket
     */
    void sendMessage(String key, PushNodeExecuteStatusDTO message);

    /**
     * @Description: 关闭连接
     */
    void close(WebSocketSession session);
}
