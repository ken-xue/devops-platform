package io.kenxue.devops.application.pipeline.logger.node.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
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
            NodeLogger nodeLogger =nodeExecuteLoggerRepository.getById(id);
            nodeLogger.deleted(UserThreadContext.get());
            nodeExecuteLoggerRepository.update(nodeLogger);
        }
        return Response.success();
    }
}
