package io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterNodeDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Mapper
public interface ClusterNodeMapper extends BaseMapper<ClusterNodeDO> {

}
