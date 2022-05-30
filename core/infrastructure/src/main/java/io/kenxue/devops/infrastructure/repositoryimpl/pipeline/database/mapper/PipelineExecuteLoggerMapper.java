package io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.dataobject.PipelineExecuteLoggerDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Mapper
public interface PipelineExecuteLoggerMapper extends BaseMapper<PipelineExecuteLoggerDO> {

}
