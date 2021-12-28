package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationPipelineDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Mapper
public interface ApplicationPipelineMapper extends BaseMapper<ApplicationPipelineDO> {

}
