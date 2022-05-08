package io.kenxue.cicd.application.pipeline.logger.node.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLogger2DTOAssembler implements Assembler<NodeExecuteLoggerDTO, NodeExecuteLogger> {

    @Override
    public NodeExecuteLoggerDTO toDTO(NodeExecuteLogger nodeExecuteLogger) {
        return NodeExecuteLogger2DTOMapStruct.INSTANCE.toDTO(nodeExecuteLogger);
    }

    @Override
    public NodeExecuteLogger toDomain(NodeExecuteLoggerDTO nodeExecuteLoggerDTO) {
        return NodeExecuteLogger2DTOMapStruct.INSTANCE.toDomain(nodeExecuteLoggerDTO);
    }

    @Override
    public List<NodeExecuteLoggerDTO> toDTOList(List<NodeExecuteLogger> nodeExecuteLoggerList) {
        return NodeExecuteLogger2DTOMapStruct.INSTANCE.toDTOList(nodeExecuteLoggerList);
    }

    @Override
    public List<NodeExecuteLogger> toDomainList(List<NodeExecuteLoggerDTO> nodeExecuteLoggerDTOList) {
        return NodeExecuteLogger2DTOMapStruct.INSTANCE.toDomainList(nodeExecuteLoggerDTOList);
    }

    @Override
    public Page<NodeExecuteLoggerDTO> toDTOPage(Page<NodeExecuteLogger> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), NodeExecuteLogger2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
