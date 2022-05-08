package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.jenkins;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.jenkins.jenkins.JenkinsListQry;
import io.kenxue.cicd.coreclient.dto.jenkins.jenkins.JenkinsPageQry;
import io.kenxue.cicd.domain.domain.jenkins.Jenkins;
import io.kenxue.cicd.domain.repository.jenkins.JenkinsRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.jenkins.database.convertor.Jenkins2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.jenkins.database.dataobject.JenkinsDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.jenkins.database.mapper.JenkinsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Slf4j
@Component
public class JenkinsRepositoryImpl implements JenkinsRepository {

    @Resource
    private JenkinsMapper jenkinsMapper;
    @Resource
    private Jenkins2DOConvector jenkins2DOConvector;

    public void create(Jenkins jenkins){
            jenkinsMapper.insert(jenkins2DOConvector.toDO(jenkins));
    }

    public void update(Jenkins jenkins){
            jenkinsMapper.updateById(jenkins2DOConvector.toDO(jenkins));
    }

    public Jenkins getById(Long id){
        return jenkins2DOConvector.toDomain(jenkinsMapper.selectById(id));
    }

    @Override
    public List<Jenkins> list(JenkinsListQry jenkinsListQry) {
        return jenkins2DOConvector.toDomainList(jenkinsMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<Jenkins> page(JenkinsPageQry qry) {
        QueryWrapper<JenkinsDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        if (Objects.nonNull(qry.getJenkinsDTO())&&Objects.nonNull(qry.getJenkinsDTO().getName()))qw.like("name",qry.getJenkinsDTO().getName());
        IPage doPage = jenkinsMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),jenkins2DOConvector.toDomainList(doPage.getRecords()));
    }
}
