package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.CaptchaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 *
 * @date 2019-02-27 5:03 PM
 */
@Mapper
public interface CaptchaMapper extends BaseMapper<CaptchaDO> {

}
