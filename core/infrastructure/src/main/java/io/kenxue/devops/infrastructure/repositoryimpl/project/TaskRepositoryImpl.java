package io.kenxue.devops.infrastructure.repositoryimpl.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.project.task.TaskListQry;
import io.kenxue.devops.domain.domain.project.Task;
import io.kenxue.devops.domain.repository.project.TaskRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor.Task2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskDO;
import io.kenxue.devops.coreclient.dto.project.task.TaskPageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;

/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Slf4j
@Component
public class TaskRepositoryImpl implements TaskRepository {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private Task2DOConvector task2DOConvector;

    public void create(Task task){
            taskMapper.insert(task2DOConvector.toDO(task));
    }

    public void update(Task task){
            taskMapper.updateById(task2DOConvector.toDO(task));
    }

    public Task getById(Long id){
        return task2DOConvector.toDomain(taskMapper.selectById(id));
    }

    @Override
    public List<Task> list(TaskListQry taskListQry) {
        return task2DOConvector.toDomainList(taskMapper.selectList(new QueryWrapper<TaskDO>().eq("deleted",false)));
    }

    @Override
    public Page<Task> page(TaskPageQry qry) {
        QueryWrapper<TaskDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = taskMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),task2DOConvector.toDomainList(doPage.getRecords()));
    }
}
