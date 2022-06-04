package io.kenxue.devops.infrastructure.repositoryimpl.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileListQry;
import io.kenxue.devops.domain.domain.project.TaskFile;
import io.kenxue.devops.domain.repository.project.TaskFileRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor.TaskFile2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskFileDO;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFilePageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.TaskFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Slf4j
@Component
public class TaskFileRepositoryImpl implements TaskFileRepository {

    @Resource
    private TaskFileMapper taskFileMapper;
    @Resource
    private TaskFile2DOConvector taskFile2DOConvector;

    public void create(TaskFile taskFile){
            taskFileMapper.insert(taskFile2DOConvector.toDO(taskFile));
    }

    public void update(TaskFile taskFile){
            taskFileMapper.updateById(taskFile2DOConvector.toDO(taskFile));
    }

    public TaskFile getById(Long id){
        return taskFile2DOConvector.toDomain(taskFileMapper.selectById(id));
    }

    @Override
    public List<TaskFile> list(TaskFileListQry taskFileListQry) {
        return taskFile2DOConvector.toDomainList(taskFileMapper.selectList(new QueryWrapper<TaskFileDO>().eq("deleted",false)));
    }

    @Override
    public Page<TaskFile> page(TaskFilePageQry qry) {
        QueryWrapper<TaskFileDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = taskFileMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),taskFile2DOConvector.toDomainList(doPage.getRecords()));
    }
}
