package io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLoggerDeleteCmdExe {
    @Resource
    private NodeExecuteLoggerRepository nodeExecuteLoggerRepository;

    public Response execute(NodeExecuteLoggerDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            NodeExecuteLogger nodeExecuteLogger =nodeExecuteLoggerRepository.getById(id);
            nodeExecuteLogger.deleted(UserThreadContext.get());
            nodeExecuteLoggerRepository.update(nodeExecuteLogger);
        }
        return Response.success();
    }
}
