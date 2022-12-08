package io.kenxue.devops.infrastructure.repositoryimpl.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerListQry;
import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import io.kenxue.devops.domain.repository.project.TaskOperatorLoggerRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor.TaskOperatorLogger2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskOperatorLoggerDO;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerPageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.TaskOperatorLoggerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;

/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Slf4j
@Component
public class TaskOperatorLoggerRepositoryImpl implements TaskOperatorLoggerRepository {

    @Resource
    private TaskOperatorLoggerMapper taskOperatorLoggerMapper;
    @Resource
    private TaskOperatorLogger2DOConvector taskOperatorLogger2DOConvector;

    public void create(TaskOperatorLogger taskOperatorLogger){
            taskOperatorLoggerMapper.insert(taskOperatorLogger2DOConvector.toDO(taskOperatorLogger));
    }

    public void update(TaskOperatorLogger taskOperatorLogger){
            taskOperatorLoggerMapper.updateById(taskOperatorLogger2DOConvector.toDO(taskOperatorLogger));
    }

    public TaskOperatorLogger getById(Long id){
        return taskOperatorLogger2DOConvector.toDomain(taskOperatorLoggerMapper.selectById(id));
    }

    @Override
    public List<TaskOperatorLogger> list(TaskOperatorLoggerListQry taskOperatorLoggerListQry) {
        return taskOperatorLogger2DOConvector.toDomainList(taskOperatorLoggerMapper.selectList(new QueryWrapper<TaskOperatorLoggerDO>().eq("deleted",false)));
    }

    @Override
    public Page<TaskOperatorLogger> page(TaskOperatorLoggerPageQry qry) {
        QueryWrapper<TaskOperatorLoggerDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = taskOperatorLoggerMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),taskOperatorLogger2DOConvector.toDomainList(doPage.getRecords()));
    }
}
