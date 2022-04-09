package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject.PipelineNodeInfoDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Mapper
public interface PipelineNodeInfoMapper extends BaseMapper<PipelineNodeInfoDO> {

}
