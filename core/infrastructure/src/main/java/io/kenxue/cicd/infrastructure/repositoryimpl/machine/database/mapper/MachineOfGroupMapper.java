package io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.dataobject.MachineOfGroupDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Mapper
public interface MachineOfGroupMapper extends BaseMapper<MachineOfGroupDO> {

}
