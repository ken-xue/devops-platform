package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject.NodeExecuteLoggerDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Mapper
public interface NodeExecuteLoggerMapper extends BaseMapper<NodeExecuteLoggerDO> {

}
