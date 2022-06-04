package io.kenxue.devops.domain.domain.project;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskWorkingHours extends CommonEntity {
    /**
     * 任务uuid
     */
    private String taskUuid;
    /**
     * 任务开始时间
     */
    private Date startTime;
    /**
     * 任务结束时间
     */
    private Date endTime;
    /**
     * 任务详细内容（富文本）
     */
    private String workDesc;
}
