package io.kenxue.devops.application.pipeline.pipeline.node.common;

import com.jcraft.jsch.ChannelShell;
import io.kenxue.devops.application.common.socket.service.WebSocketService;
import io.kenxue.devops.application.pipeline.pipeline.manager.ChannelManager;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.devops.sharedataboject.common.command.CommandConst;
import io.kenxue.devops.sharedataboject.pipeline.constant.NodeConst;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 节点抽象
 * @author 麦奇
 */
@Slf4j
public abstract class AbstractNode implements Node {

    @Resource
    @Qualifier("pipelineNodeExecuteLoggerSocketServiceImpl")
    private WebSocketService loggerService;
    @Resource
    private ChannelManager channelManager;

    public int shell(NodeLogger logger, ExecuteContext executeContext, String... commands) {
        String loggerUuid = (String) executeContext.getAttributes(getName() + NodeConst.LOGGER_UUID_KEY);
        String nodeUuid = (String) executeContext.getAttributes(getName() + NodeConst.NODE_UUID_KEY);
        logger.setLoggerUuid(loggerUuid);
        logger.setNodeUuid(nodeUuid);
        String key = String.format("%s&%s", loggerUuid, nodeUuid);
        ChannelShell channel;
        InputStream in = null;
        OutputStream os = null;
        int returnCode = -1;
        try {
            channel = channelManager.getChannelShell();
            in = channel.getInputStream();
            channel.setPty(true);
            channel.connect();
            os = channel.getOutputStream();
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
                    log.info("推送日志数据 key:{}", key);
                    logger.append(resp);
                    loggerService.sendMessage(key, resp.getBytes(StandardCharsets.UTF_8));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    log.info("exit-status: " + channel.getExitStatus());
                    break;
                }
            }
            //主动释放当前socket连接
            loggerService.close(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnCode;
    }
}
