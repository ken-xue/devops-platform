package io.kenxue.cicd.application.pipeline.pipeline.node;

import com.jcraft.jsch.ChannelShell;
import io.kenxue.cicd.application.pipeline.logger.node.service.PipelineExecuteLoggerSocketService;
import io.kenxue.cicd.application.pipeline.pipeline.manager.ChannelManager;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.cicd.sharedataboject.pipeline.constant.CommandConst;
import io.kenxue.cicd.sharedataboject.pipeline.constant.NodeConst;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 节点抽象
 * @author 麦奇
 */
@Slf4j
public abstract class AbstractNode implements Node {

    @Resource
    private PipelineExecuteLoggerSocketService loggerService;
    @Resource
    private NodeExecuteLoggerRepository loggerRepository;
    @Resource
    private ChannelManager channelManager;

    public int shell(NodeLogger nodeLogger, ExecuteContext executeContext, String... commands) {
        String loggerUuid = (String) executeContext.getAttributes(getName() + NodeConst.LOGGER_UUID_KEY);
        String nodeUuid = (String) executeContext.getAttributes(getName() + NodeConst.NODE_UUID_KEY);
        nodeLogger.setLoggerUuid(loggerUuid);
        nodeLogger.setNodeUuid(nodeUuid);
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

//            long startTime = System.currentTimeMillis();
            byte[] buffer = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(buffer, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    String resp = new String(buffer, 0, i);
                    if (resp.indexOf(NodeConst.MORE) >= 0) {
                        os.write((NodeConst.BLANK).getBytes());
                        os.flush();
                    }
                    log.debug(resp);
//                    startTime = System.currentTimeMillis();
                    log.info("推送日志数据 key:{}", key);
                    nodeLogger.append(commandBytes + resp);
                    loggerService.sendMessage(key, commandBytes + resp);
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    log.info("exit-status: " + channel.getExitStatus());
                    break;
                }
                //十秒钟未响应退出
//                if (System.currentTimeMillis() - startTime > 10000) {
//                    loggerService.sendMessage(key, String.format("%s%s%s", CommandConst.ENTER,"响应超时", CommandConst.ENTER));
//                    break;
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                in.close();
                nodeLogger.create();
                nodeLogger.serializable();//序列化
                nodeLogger.setExecuteEndTime(new Date());
                loggerRepository.create(nodeLogger);
                //主动释放当前socket连接
                loggerService.close(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnCode;
    }
}
