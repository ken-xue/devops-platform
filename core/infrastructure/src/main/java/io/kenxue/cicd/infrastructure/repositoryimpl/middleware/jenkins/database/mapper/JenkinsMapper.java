package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.jenkins.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.jenkins.database.dataobject.JenkinsDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Mapper
public interface JenkinsMapper extends BaseMapper<JenkinsDO> {

}
