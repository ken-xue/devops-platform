package io.kenxue.devops.infrastructure.repositoryimpl.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupListQry;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import io.kenxue.devops.domain.repository.project.TaskGroupRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor.TaskGroup2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskGroupDO;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupPageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.TaskGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Slf4j
@Component
public class TaskGroupRepositoryImpl implements TaskGroupRepository {

    @Resource
    private TaskGroupMapper taskGroupMapper;
    @Resource
    private TaskGroup2DOConvector taskGroup2DOConvector;

    public void create(TaskGroup taskGroup){
            taskGroupMapper.insert(taskGroup2DOConvector.toDO(taskGroup));
    }

    public void update(TaskGroup taskGroup){
            taskGroupMapper.updateById(taskGroup2DOConvector.toDO(taskGroup));
    }

    public TaskGroup getById(Long id){
        return taskGroup2DOConvector.toDomain(taskGroupMapper.selectById(id));
    }

    @Override
    public List<TaskGroup> list(TaskGroupListQry taskGroupListQry) {
        QueryWrapper<TaskGroupDO> qw = new QueryWrapper<>();
        qw.eq("project_uuid",taskGroupListQry.getProjectUuid());
        List<TaskGroupDO> list = taskGroupMapper.selectList(qw.eq("deleted", false));
        return taskGroup2DOConvector.toDomainList(list);
    }

    @Override
    public Page<TaskGroup> page(TaskGroupPageQry qry) {
        QueryWrapper<TaskGroupDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = taskGroupMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),taskGroup2DOConvector.toDomainList(doPage.getRecords()));
    }
}
