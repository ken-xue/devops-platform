package io.kenxue.cicd.coreclient.dto.machine.machinegroup;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Data
@Accessors(chain = true)
public class MachineGroupDTO extends CommonDTO {
    /**
     * 分组名
     */
    private String name;
    private String remark;
}
