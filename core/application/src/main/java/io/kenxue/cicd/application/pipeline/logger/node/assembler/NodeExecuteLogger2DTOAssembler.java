package io.kenxue.cicd.application.pipeline.logger.node.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLogger2DTOAssembler implements Assembler<NodeExecuteLoggerDTO, NodeLogger> {

    @Override
    public NodeExecuteLoggerDTO toDTO(NodeLogger nodeLogger) {
        return NodeExecuteLogger2DTOMapStruct.INSTANCE.toDTO(nodeLogger);
    }

    @Override
    public NodeLogger toDomain(NodeExecuteLoggerDTO nodeExecuteLoggerDTO) {
        return NodeExecuteLogger2DTOMapStruct.INSTANCE.toDomain(nodeExecuteLoggerDTO);
    }

    @Override
    public List<NodeExecuteLoggerDTO> toDTOList(List<NodeLogger> nodeLoggerList) {
        return NodeExecuteLogger2DTOMapStruct.INSTANCE.toDTOList(nodeLoggerList);
    }

    @Override
    public List<NodeLogger> toDomainList(List<NodeExecuteLoggerDTO> nodeExecuteLoggerDTOList) {
        return NodeExecuteLogger2DTOMapStruct.INSTANCE.toDomainList(nodeExecuteLoggerDTOList);
    }

    @Override
    public Page<NodeExecuteLoggerDTO> toDTOPage(Page<NodeLogger> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), NodeExecuteLogger2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
