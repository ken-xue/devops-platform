package io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.service;

import io.kenxue.cicd.coreclient.api.pipeline.NodeExecuteLoggerAppService;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.*;
import io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.command.NodeExecuteLoggerAddCmdExe;
import io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.command.NodeExecuteLoggerDeleteCmdExe;
import io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.command.NodeExecuteLoggerUpdateCmdExe;
import io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.command.query.NodeExecuteLoggerGetQryExe;
import io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.command.query.NodeExecuteLoggerListQryExe;
import io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.command.query.NodeExecuteLoggerPageQryExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
