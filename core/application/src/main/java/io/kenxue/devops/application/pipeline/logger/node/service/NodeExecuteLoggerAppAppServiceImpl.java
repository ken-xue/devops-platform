package io.kenxue.devops.application.pipeline.logger.node.service;

import io.kenxue.devops.application.pipeline.logger.node.command.query.NodeLoggerGetQryExe;
import io.kenxue.devops.coreclient.api.pipeline.NodeExecuteLoggerAppService;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.*;
import io.kenxue.devops.application.pipeline.logger.node.command.NodeExecuteLoggerAddCmdExe;
import io.kenxue.devops.application.pipeline.logger.node.command.NodeExecuteLoggerDeleteCmdExe;
import io.kenxue.devops.application.pipeline.logger.node.command.NodeExecuteLoggerUpdateCmdExe;
import io.kenxue.devops.application.pipeline.logger.node.command.query.NodeExecuteLoggerGetQryExe;
import io.kenxue.devops.application.pipeline.logger.node.command.query.NodeExecuteLoggerListQryExe;
import io.kenxue.devops.application.pipeline.logger.node.command.query.NodeExecuteLoggerPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Service
public class NodeExecuteLoggerAppAppServiceImpl implements NodeExecuteLoggerAppService {
    @Resource
    private NodeExecuteLoggerAddCmdExe nodeExecuteLoggerAddCmdExe;
    @Resource
    private NodeExecuteLoggerUpdateCmdExe nodeExecuteLoggerUpdateCmdExe;
    @Resource
    private NodeExecuteLoggerGetQryExe nodeExecuteLoggerGetQryExe;
    @Resource
    private NodeExecuteLoggerListQryExe nodeExecuteLoggerListQryExe;
    @Resource
    private NodeExecuteLoggerPageQryExe nodeExecuteLoggerPageQryExe;
    @Resource
    private NodeExecuteLoggerDeleteCmdExe nodeExecuteLoggerDeleteCmdExe;
    @Resource
    private NodeLoggerGetQryExe nodeLoggerGetQryExe;

    public Response add(NodeExecuteLoggerAddCmd nodeExecuteLoggerAddCmd) {
        return  nodeExecuteLoggerAddCmdExe.execute(nodeExecuteLoggerAddCmd);
    }

    public Response update(NodeExecuteLoggerUpdateCmd cmd) {
        return nodeExecuteLoggerUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<NodeExecuteLoggerDTO> getById(NodeExecuteLoggerGetQry qry) {
        return nodeExecuteLoggerGetQryExe.execute(qry);
    }

    public MultiResponse<NodeExecuteLoggerDTO> list(NodeExecuteLoggerListQry qry) {
        return nodeExecuteLoggerListQryExe.execute(qry);
    }

    public Response delete(NodeExecuteLoggerDeleteCmd nodeExecuteLoggerDeleteCmd) {
        return nodeExecuteLoggerDeleteCmdExe.execute(nodeExecuteLoggerDeleteCmd);
    }

    public PageResponse<NodeExecuteLoggerDTO> page(NodeExecuteLoggerPageQry nodeExecuteLoggerPageQry) {
        return nodeExecuteLoggerPageQryExe.execute(nodeExecuteLoggerPageQry);
    }

    @Override
    public Response getNodeAndLoggerUuId(NodeExecuteLoggerGetQry nodeExecuteLoggerGetQry) {
        return nodeLoggerGetQryExe.execute(nodeExecuteLoggerGetQry);
    }

}
