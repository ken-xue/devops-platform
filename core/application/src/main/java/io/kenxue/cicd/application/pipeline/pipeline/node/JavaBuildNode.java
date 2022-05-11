package io.kenxue.cicd.application.pipeline.pipeline.node;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.kenxue.cicd.application.pipeline.logger.node.service.PipelineExecuteLoggerSocketService;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.factory.pipeline.NodeExecuteLoggerFactory;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.cicd.sharedataboject.pipeline.context.DefaultResult;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Properties;

/**
 * java构建step
 */
@Service
@Slf4j
public class JavaBuildNode extends AbstractNode {

    @Override
    public Result execute(ExecuteContext executeContext) {
        NodeLogger logger = NodeExecuteLoggerFactory.getNodeExecuteLogger().setExecuteStartTime(new Date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error(getName());
        Object attributes = executeContext.getAttributes(getName());
        DefaultResult defaultResult = new DefaultResult();
        log.info("attr : {}",attributes);


        //===================test==================
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession("root", "101.200.54.149", 22);
            session.setPassword("Rh990828"); // 设置密码
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(50000); // 设置timeout时间
            session.connect(); // 通过Session建立链接
            shellCmd(logger,executeContext,session,new String[]{
                    "cd /home/admin/"
                    ,"git -v"
                    ,"git clone https://gitee.com/ken_xue/cicd-platform.git"
                    ,"cd cicd-platform"
                    ,"mvn -v"
                    ,"mvn clean install"
                    ,"cd startar"
                    ,"ls target"
            });
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.error("{}节点执行完成",getName());
        return defaultResult;
    }

    @Override
    public String getName() {
        return "JAVA_BUILD";
    }

//    public int shellCmd(NodeExecuteLogger logger,ExecuteContext executeContext,Session session,String... cmds) {
//        StringBuilder sb = new StringBuilder();
//        String loggerUuid = (String) executeContext.getAttributes(getName() + "logger-uuid");
//        logger.setLoggerUuid(loggerUuid);
//        String nodeUuid = (String) executeContext.getAttributes(getName() + "node-uuid");
//        logger.setNodeUuid(nodeUuid);
//        String key = String.format("%s&%s", loggerUuid, nodeUuid);
//        ChannelShell channel = null;
//        InputStream in = null;
//        OutputStream os = null;
//        int returnCode = -1;
//        try {
//            System.out.println("执行如下命令:\n" + String.join("\n", cmds));
//            channel = (ChannelShell) session.openChannel("shell");
//            in = channel.getInputStream();
//            channel.setPty(true);
//            channel.connect();
//            os = channel.getOutputStream();
//            StringBuilder cmdsb = new StringBuilder();
//            for (String cmd : cmds) {
//                cmdsb.append(cmd).append("\r\n");
//            }
//                byte[] executeCMD = cmdsb.toString().getBytes();
//                os.write(executeCMD);
//                os.flush();
//
//                long startTime = System.currentTimeMillis();
//                byte[] tmp=new byte[1024];
//                while(true){
//                    while(in.available()>0){
//                        int i=in.read(tmp, 0, 1024);
//                        if(i<0)break;
//                        String resp = new String(tmp, 0, i);
//                        if(resp.indexOf("--More--") >= 0){
//                            os.write((" ").getBytes());
//                            os.flush();
//                        }
//                        System.out.print(resp);
//                        startTime = System.currentTimeMillis();
//                        log.info("推送日志数据:{}",key);
//                        sb.append(executeCMD).append(resp);
//                        loggerService.sendMessage(key, executeCMD + resp);
//                    }
//                    if(channel.isClosed()){
//                        if(in.available()>0) continue;
//                        System.out.println("exit-status: "+channel.getExitStatus());
//                        break;
//                    }
//                    long endTime = System.currentTimeMillis();
//                    if(endTime-startTime>10000) {//十秒钟未响应退出
//                        loggerService.sendMessage(key, "\r\n"+"十秒未响应退出");
//                        break;
//                    }
//                }
//
////                BufferedReader br = new BufferedReader(new InputStreamReader(in));
////                String msg = null;
////                while((msg = br.readLine())!=null){
////                    System.out.println(msg);
////                }
////                br.close();
//
//
////                byte[] buf = new byte[1024];
////                long startTime = System.currentTimeMillis();
////                while(true){
////                    while(in.available()>0){
////                        int len=in.read(buf,0,buf.length);
////                        if(len<0){ break; }
////                        String resp = new String(buf, 0, len);
////                        String key = String.format("%s&%s", loggerUuid, nodeUuid);
////                        log.info("推送日志数据:{}",key);
////                        sb.append(executeCMD).append(resp);
////                        loggerService.sendMessage(key, executeCMD + resp);
////                    }
////                    if(channel.isClosed()){
////                        if(in.available()>0){
////                            continue;
////                        }
////                        System.out.println("exit-status"+channel.getExitStatus());
////                        break;
////                    }
////                    long endTime = System.currentTimeMillis();
////                    if(endTime-startTime>10000) {
////                        break;
////                    }
////                    Thread.sleep(500);
////                }
//
////                TimeUnit.SECONDS.sleep(5);
////                byte[] tmp = new byte[1024];
////                    while (in.available() > 0) {
////                        int i = in.read(tmp, 0, 1024);
////                        if (i < 0) { break; }
////                        String resp = new String(tmp, 0, i);
////                        System.out.println(resp);
////                        String key = String.format("%s&%s", loggerUuid, nodeUuid);
////                        log.info("推送日志数据:{}",key);
////                        sb.append(executeCMD).append(resp);
////                        loggerService.sendMessage(key, executeCMD + resp);
////                    }
////            }
//            os.write(("exit" + "\r\n").getBytes());
//            os.flush();
//        } catch (Exception e) {
//           e.printStackTrace();
//        } finally {
//            try {
//                os.close();
//                in.close();
//                channel.disconnect();
//                logger.create("");
//                logger.setLogger(sb.toString());
//                logger.setExecuteEndTime(new Date());
//                loggerRepository.create(logger);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return returnCode;
//    }
}

