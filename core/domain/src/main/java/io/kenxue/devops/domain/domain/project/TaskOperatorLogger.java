package io.kenxue.devops.domain.domain.project;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskOperatorLogger extends CommonEntity {
    /**
     * 项目名称
     */
    private String taskUuid;
    /**
     * 操作人
     */
    private String userUuid;
    /**
     * 内容
     */
    private String content;
}
