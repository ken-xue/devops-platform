package io.kenxue.devops.application.pipeline.pipeline.node.common;

import com.jcraft.jsch.ChannelShell;
import io.kenxue.devops.application.common.socket.service.WebSocketService;
import io.kenxue.devops.application.pipeline.pipeline.manager.ChannelManager;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.sharedataboject.common.command.CommandConst;
import io.kenxue.devops.sharedataboject.pipeline.constant.NodeConst;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.node.Node;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 节点抽象
 * @author 麦奇
 */
@Slf4j
public abstract class AbstractNode implements Node {

    @Lazy
    @Resource
    @Qualifier("pipelineNodeExecuteLoggerSocketServiceImpl")
    private WebSocketService loggerService;

    @Lazy
    @Resource
    private ChannelManager channelManager;

    public int shell(NodeLogger logger, ExecuteContext executeContext, String... commands) {
        String loggerUuid = (String) executeContext.getAttributes(getName() + NodeConst.LOGGER_UUID_KEY);
        String nodeUuid = (String) executeContext.getAttributes(getName() + NodeConst.NODE_UUID_KEY);
        logger.setLoggerUuid(loggerUuid);
        logger.setNodeUuid(nodeUuid);
        String key = String.format("%s&%s", loggerUuid, nodeUuid);

        int ret = -1;
        ChannelShell channel = channelManager.getChannelShell();
        try (InputStream in = channel.getInputStream(); OutputStream os = channel.getOutputStream()){
            //拼接命令
            StringBuilder cmdsb = new StringBuilder();
            for (String cmd : commands) {
                cmdsb.append(cmd).append(CommandConst.ENTER);
            }

            cmdsb.append(CommandConst.EXIT);

            byte[] commandBytes = cmdsb.toString().getBytes();
            os.write(commandBytes);
            os.flush();

            byte[] buffer = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int index = in.read(buffer, 0, 1024);
                    if (index < 0) {
                        break;
                    }
                    String resp = new String(buffer, 0, index);
                    if (resp.indexOf(NodeConst.MORE) >= 0) {
                        os.write((NodeConst.BLANK).getBytes());
                        os.flush();
                    }
                    if (log.isDebugEnabled()) {
                        log.debug(resp);
                    }
                    log.debug("推送日志数据 key:{}", key);
                    logger.append(resp);
                    loggerService.sendMessage(key, resp.getBytes(StandardCharsets.UTF_8));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    log.debug("exit-status: " + channel.getExitStatus());
                    break;
                }
            }
            //主动释放当前socket连接
            loggerService.close(key);
            channel.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            channel.disconnect();
        }
        return ret;
    }

    public String cmd(String ...cmd){
        StringBuilder sb = new StringBuilder();
        for (String c:cmd)sb.append(c);
        return sb.toString();
    }
}