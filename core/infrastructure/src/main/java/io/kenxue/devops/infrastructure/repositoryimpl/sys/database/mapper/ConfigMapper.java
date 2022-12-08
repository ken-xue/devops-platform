package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.ConfigDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Mapper
public interface ConfigMapper extends BaseMapper<ConfigDO> {

}
