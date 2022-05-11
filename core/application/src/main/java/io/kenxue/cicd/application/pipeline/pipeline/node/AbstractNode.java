package io.kenxue.cicd.application.pipeline.pipeline.node;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.kenxue.cicd.application.pipeline.logger.node.service.PipelineExecuteLoggerSocketService;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * 节点抽象
 */
@Slf4j
public abstract class AbstractNode implements Node {

    @Resource
    private PipelineExecuteLoggerSocketService loggerService;
    @Resource
    private NodeExecuteLoggerRepository loggerRepository;

    public int shell(NodeLogger nodeLogger, ExecuteContext executeContext, String... commands) {
        String loggerUuid = (String) executeContext.getAttributes(getName() + "logger-uuid");
        String nodeUuid = (String) executeContext.getAttributes(getName() + "node-uuid");
        nodeLogger.setLoggerUuid(loggerUuid);
        nodeLogger.setNodeUuid(nodeUuid);
        String key = String.format("%s&%s", loggerUuid, nodeUuid);
        Session session = null;
        ChannelShell channel = null;
        InputStream in = null;
        OutputStream os = null;
        int returnCode = -1;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession("root", "101.20.42.149", 22);
            session.setPassword("321232"); // 设置密码
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(50000); // 设置timeout时间
            session.connect(); // 通过Session建立链接
            log.debug("执行如下命令:\n" + String.join("\n", commands));


            channel = (ChannelShell) session.openChannel("shell");
            in = channel.getInputStream();
            channel.setPty(true);
            channel.connect();
            os = channel.getOutputStream();
            StringBuilder cmdsb = new StringBuilder();
            for (String cmd : commands) {
                cmdsb.append(cmd).append("\r\n");
            }
            cmdsb.append("exit" + "\r\n");
            byte[] commandBytes = cmdsb.toString().getBytes();
            os.write(commandBytes);
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
                    log.info("推送日志数据:{}",key);
                    nodeLogger.append(commandBytes);
                    nodeLogger.append(resp.getBytes());
                    loggerService.sendMessage(key, commandBytes + resp);
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
                session.disconnect();
                nodeLogger.create();
                nodeLogger.serializable();
                nodeLogger.setExecuteEndTime(new Date());
                loggerRepository.create(nodeLogger);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnCode;
    }
}
