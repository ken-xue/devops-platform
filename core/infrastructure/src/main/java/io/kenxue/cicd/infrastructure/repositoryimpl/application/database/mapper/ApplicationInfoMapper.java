package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationInfoDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Mapper
public interface ApplicationInfoMapper extends BaseMapper<ApplicationInfoDO> {

}
