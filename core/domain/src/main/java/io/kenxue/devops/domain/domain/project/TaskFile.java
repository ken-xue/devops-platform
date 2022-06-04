package io.kenxue.devops.domain.domain.project;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskFile extends CommonEntity {
    /**
     * 任务uuid
     */
    private String taskUuid;
    /**
     * 文件名称
     */
    private Date fileName;
    /**
     * 文件路径
     */
    private Date url;
}
