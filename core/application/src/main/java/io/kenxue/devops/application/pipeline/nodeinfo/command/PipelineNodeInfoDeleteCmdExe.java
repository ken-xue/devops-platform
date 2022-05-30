package io.kenxue.devops.application.pipeline.nodeinfo.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.pipeline.PipelineNodeInfoRepository;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Component
public class PipelineNodeInfoDeleteCmdExe {
    @Resource
    private PipelineNodeInfoRepository pipelineNodeInfoRepository;

    public Response execute(PipelineNodeInfoDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            PipelineNodeInfo pipelineNodeInfo =pipelineNodeInfoRepository.getById(id);
            pipelineNodeInfo.deleted(UserThreadContext.get());
            pipelineNodeInfoRepository.update(pipelineNodeInfo);
        }
        return Response.success();
    }
}
