package io.kenxue.devops.domain.domain.project;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskOfUser extends CommonEntity {
    /**
     * 项目uuid
     */
    private String taskUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
}
