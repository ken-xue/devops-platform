package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.MachineInfoDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Mapper
public interface MachineInfoMapper extends BaseMapper<MachineInfoDO> {

}
