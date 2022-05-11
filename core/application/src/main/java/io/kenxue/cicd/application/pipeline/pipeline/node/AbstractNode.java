package io.kenxue.cicd.application.pipeline.pipeline.node;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;
import io.kenxue.cicd.application.pipeline.logger.node.service.PipelineExecuteLoggerSocketService;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 节点抽象
 */
public abstract class AbstractNode implements Node {

    @Resource
    private PipelineExecuteLoggerSocketService loggerService;
    @Resource
    private NodeExecuteLoggerRepository loggerRepository;

    public int shellCmd(NodeLogger nodeLogger, ExecuteContext executeContext, Session session, String... cmds) {
        StringBuilder sb = new StringBuilder();
        String loggerUuid = (String) executeContext.getAttributes(getName() + "logger-uuid");
        nodeLogger.setLoggerUuid(loggerUuid);
        String nodeUuid = (String) executeContext.getAttributes(getName() + "node-uuid");
        nodeLogger.setNodeUuid(nodeUuid);
        String key = String.format("%s&%s", loggerUuid, nodeUuid);
        ChannelShell channel = null;
        InputStream in = null;
        OutputStream os = null;
        int returnCode = -1;
        try {
            System.out.println("执行如下命令:\n" + String.join("\n", cmds));
            channel = (ChannelShell) session.openChannel("shell");
            in = channel.getInputStream();
            channel.setPty(true);
            channel.connect();
            os = channel.getOutputStream();
            StringBuilder cmdsb = new StringBuilder();
            for (String cmd : cmds) {
                cmdsb.append(cmd).append("\r\n");
            }
            cmdsb.append("exit" + "\r\n");
            byte[] executeCMD = cmdsb.toString().getBytes();
            os.write(executeCMD);
            os.flush();

            long startTime = System.currentTimeMillis();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    String resp = new String(tmp, 0, i);
                    if (resp.indexOf("--More--") >= 0) {
                        os.write((" ").getBytes());
                        os.flush();
                    }
                    System.out.print(resp);
                    startTime = System.currentTimeMillis();
                    //log.info("推送日志数据:{}",key);
                    sb.append(executeCMD).append(resp);
                    loggerService.sendMessage(key, executeCMD + resp);
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                long endTime = System.currentTimeMillis();
                if (endTime - startTime > 10000) {//十秒钟未响应退出
                    loggerService.sendMessage(key, String.format("\r\n%s\r\n" + "响应超时"));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                in.close();
                channel.disconnect();
                nodeLogger.create("");
                nodeLogger.setLogger(sb.toString());
                nodeLogger.setExecuteEndTime(new Date());
                loggerRepository.create(nodeLogger);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnCode;
    }
}
