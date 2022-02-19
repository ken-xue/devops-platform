package io.kenxue.cicd.domain.domain.project;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectOfUser extends CommonEntity {
    /**
     * 项目uuid
     */
    private String projectUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
}
