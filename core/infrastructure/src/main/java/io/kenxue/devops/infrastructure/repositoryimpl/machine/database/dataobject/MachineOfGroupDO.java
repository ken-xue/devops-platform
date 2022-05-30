package io.kenxue.devops.infrastructure.repositoryimpl.machine.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Data
@TableName("machine_of_group")
public class MachineOfGroupDO extends CommonEntity {
    /**
     * 分组uuid
     */
    private String groupUuid;
    /**
     * 机器uuid
     */
    private String machineUuid;
}
