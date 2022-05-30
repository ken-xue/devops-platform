package io.kenxue.devops.infrastructure.repositoryimpl.machine.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.machine.database.dataobject.MachineGroupDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Mapper
public interface MachineGroupMapper extends BaseMapper<MachineGroupDO> {

}
