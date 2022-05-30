package io.kenxue.devops.infrastructure.repositoryimpl.machine.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Data
@TableName("machine_group")
public class MachineGroupDO extends CommonEntity {
    /**
     * 分组名
     */
    private String name;
    private String remark;
}
