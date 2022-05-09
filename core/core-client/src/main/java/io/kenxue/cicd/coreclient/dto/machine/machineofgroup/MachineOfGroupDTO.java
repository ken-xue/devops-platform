package io.kenxue.cicd.coreclient.dto.machine.machineofgroup;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Data
@Accessors(chain = true)
public class MachineOfGroupDTO extends CommonDTO {
    /**
     * 分组uuid
     */
    private String groupUuid;
    /**
     * 机器uuid
     */
    private String machineUuid;
}
