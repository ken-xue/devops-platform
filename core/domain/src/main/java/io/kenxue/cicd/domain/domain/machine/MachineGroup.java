package io.kenxue.cicd.domain.domain.machine;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineGroup extends CommonEntity {
    /**
     * 分组名
     */
    private String name;
    private String remark;
}
