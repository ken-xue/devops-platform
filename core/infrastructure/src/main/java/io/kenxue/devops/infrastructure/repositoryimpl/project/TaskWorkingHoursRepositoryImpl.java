package io.kenxue.devops.infrastructure.repositoryimpl.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursListQry;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import io.kenxue.devops.domain.repository.project.TaskWorkingHoursRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor.TaskWorkingHours2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskWorkingHoursDO;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursPageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.TaskWorkingHoursMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Slf4j
@Component
public class TaskWorkingHoursRepositoryImpl implements TaskWorkingHoursRepository {

    @Resource
    private TaskWorkingHoursMapper taskWorkingHoursMapper;
    @Resource
    private TaskWorkingHours2DOConvector taskWorkingHours2DOConvector;

    public void create(TaskWorkingHours taskWorkingHours){
            taskWorkingHoursMapper.insert(taskWorkingHours2DOConvector.toDO(taskWorkingHours));
    }

    public void update(TaskWorkingHours taskWorkingHours){
            taskWorkingHoursMapper.updateById(taskWorkingHours2DOConvector.toDO(taskWorkingHours));
    }

    public TaskWorkingHours getById(Long id){
        return taskWorkingHours2DOConvector.toDomain(taskWorkingHoursMapper.selectById(id));
    }

    @Override
    public List<TaskWorkingHours> list(TaskWorkingHoursListQry taskWorkingHoursListQry) {
        return taskWorkingHours2DOConvector.toDomainList(taskWorkingHoursMapper.selectList(new QueryWrapper<TaskWorkingHoursDO>().eq("deleted",false)));
    }

    @Override
    public Page<TaskWorkingHours> page(TaskWorkingHoursPageQry qry) {
        QueryWrapper<TaskWorkingHoursDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = taskWorkingHoursMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),taskWorkingHours2DOConvector.toDomainList(doPage.getRecords()));
    }
}
