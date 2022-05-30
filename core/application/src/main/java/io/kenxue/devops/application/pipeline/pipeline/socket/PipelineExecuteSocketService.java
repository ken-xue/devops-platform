//package io.kenxue.devops.application.pipeline.pipeline.socket;
//
//import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PushNodeExecuteStatusDTO;
//import org.springframework.web.socket.WebSocketSession;
//
///**
// * @Description: 流水节点状态
// */
//public interface PipelineExecuteSocketService {
//    /**
//     * @Description: 初始化ssh连接
//     */
//    void initConnection(WebSocketSession session);
//
//    /**
//     * @Description: 处理客户端发的数据
//     */
//    void recvHandle(String buffer, WebSocketSession session);
//
//    /**
//     * @Description: 数据写回前端
//     */
//    void sendMessage(String key, PushNodeExecuteStatusDTO message);
//
//    /**
//     * @Description: 关闭连接
//     */
//    void close(WebSocketSession session);
//}
