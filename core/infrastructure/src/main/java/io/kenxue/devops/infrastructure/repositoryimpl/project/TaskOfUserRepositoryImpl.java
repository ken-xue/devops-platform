package io.kenxue.devops.infrastructure.repositoryimpl.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserListQry;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import io.kenxue.devops.domain.repository.project.TaskOfUserRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor.TaskOfUser2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskOfUserDO;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserPageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.TaskOfUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;

/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Slf4j
@Component
public class TaskOfUserRepositoryImpl implements TaskOfUserRepository {

    @Resource
    private TaskOfUserMapper taskOfUserMapper;
    @Resource
    private TaskOfUser2DOConvector taskOfUser2DOConvector;

    public void create(TaskOfUser taskOfUser){
            taskOfUserMapper.insert(taskOfUser2DOConvector.toDO(taskOfUser));
    }

    public void update(TaskOfUser taskOfUser){
            taskOfUserMapper.updateById(taskOfUser2DOConvector.toDO(taskOfUser));
    }

    public TaskOfUser getById(Long id){
        return taskOfUser2DOConvector.toDomain(taskOfUserMapper.selectById(id));
    }

    @Override
    public List<TaskOfUser> list(TaskOfUserListQry taskOfUserListQry) {
        return taskOfUser2DOConvector.toDomainList(taskOfUserMapper.selectList(new QueryWrapper<TaskOfUserDO>().eq("deleted",false)));
    }

    @Override
    public Page<TaskOfUser> page(TaskOfUserPageQry qry) {
        QueryWrapper<TaskOfUserDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = taskOfUserMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),taskOfUser2DOConvector.toDomainList(doPage.getRecords()));
    }
}
