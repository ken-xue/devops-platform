package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.PipelineNodeDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Mapper
public interface PipelineNodeMapper extends BaseMapper<PipelineNodeDO> {

}
