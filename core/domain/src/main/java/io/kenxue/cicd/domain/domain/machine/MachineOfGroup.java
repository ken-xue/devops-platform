package io.kenxue.cicd.domain.domain.machine;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineOfGroup extends CommonEntity {
    /**
     * 分组uuid
     */
    private String groupUuid;
    /**
     * 机器uuid
     */
    private String machineUuid;
}
