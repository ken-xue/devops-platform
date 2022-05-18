package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.zookeeper.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.zookeeper.database.dataobject.ZookeeperDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Mapper
public interface ZookeeperMapper extends BaseMapper<ZookeeperDO> {

}
