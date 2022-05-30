package io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterNodeDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Mapper
public interface ClusterNodeMapper extends BaseMapper<ClusterNodeDO> {

}
