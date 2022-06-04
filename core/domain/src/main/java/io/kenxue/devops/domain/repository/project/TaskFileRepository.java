package io.kenxue.devops.domain.repository.project;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileListQry;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFilePageQry;
import io.kenxue.devops.domain.domain.project.TaskFile;
import java.util.List;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public interface TaskFileRepository {
    void create(TaskFile taskFile);
    void update(TaskFile taskFile);
    TaskFile getById(Long id);
    List<TaskFile> list(TaskFileListQry taskFileListQry);
    Page<TaskFile> page(TaskFilePageQry qry);
}
