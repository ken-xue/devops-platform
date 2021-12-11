package io.kenxue.cicd.infrastructure.repositoryimpl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.log.LogListQry;
import io.kenxue.cicd.coreclient.dto.sys.log.LogPageQry;
import io.kenxue.cicd.domain.domain.sys.Log;
import io.kenxue.cicd.domain.repository.sys.LogRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Log2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.LogDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper.LogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Slf4j
@Component
public class LogRepositoryImpl implements LogRepository {

    @Resource
    private LogMapper logMapper;
    @Resource
    private Log2DOConvector log2DOConvector;

    public void create(Log log){
        logMapper.insert(log2DOConvector.toDO(log));
    }

    public void update(Log log){
        logMapper.updateById(log2DOConvector.toDO(log));
    }
    
    public Log getById(Long id){
        return log2DOConvector.toDomain(logMapper.selectById(id));
    }

    @Override
    public List<Log> list(LogListQry logListQry) {
        return log2DOConvector.toDomainList(logMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<Log> page(LogPageQry qry) {
        QueryWrapper<LogDO> qw = new QueryWrapper<>();
        IPage doPage = logMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),log2DOConvector.toDomainList(doPage.getRecords()));
    }
}
