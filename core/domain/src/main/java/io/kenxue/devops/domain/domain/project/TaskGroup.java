package io.kenxue.devops.domain.domain.project;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskGroup extends CommonEntity {
    /**
     * 项目uuid
     */
    private String projectUuid;
    /**
     * 任务组名
     */
    private String groupName;
}
