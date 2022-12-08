package io.kenxue.devops.infrastructure.repositoryimpl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigListQry;
import io.kenxue.devops.domain.domain.sys.Config;
import io.kenxue.devops.domain.repository.sys.ConfigRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor.Config2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.ConfigDO;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigPageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper.ConfigMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import java.util.List;

/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Slf4j
@Component
public class ConfigRepositoryImpl implements ConfigRepository {

    @Resource
    private ConfigMapper configMapper;
    @Resource
    private Config2DOConvector config2DOConvector;

    public void create(Config config){
            configMapper.insert(config2DOConvector.toDO(config));
    }

    public void update(Config config){
            configMapper.updateById(config2DOConvector.toDO(config));
    }

    public Config getById(Long id){
        return config2DOConvector.toDomain(configMapper.selectById(id));
    }

    @Override
    public List<Config> list(ConfigListQry configListQry) {
        return config2DOConvector.toDomainList(configMapper.selectList(new QueryWrapper<ConfigDO>().eq("deleted",false)));
    }

    @Override
    public Page<Config> page(ConfigPageQry qry) {
        QueryWrapper<ConfigDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = configMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),config2DOConvector.toDomainList(doPage.getRecords()));
    }
}
