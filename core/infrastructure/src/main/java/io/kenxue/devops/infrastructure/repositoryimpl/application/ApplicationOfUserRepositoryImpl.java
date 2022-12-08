package io.kenxue.devops.infrastructure.repositoryimpl.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserListQry;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserPageQry;
import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import io.kenxue.devops.domain.repository.application.ApplicationOfUserRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor.ApplicationOfUser2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationOfUserDO;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.mapper.ApplicationOfUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;

/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@Component
public class ApplicationOfUserRepositoryImpl implements ApplicationOfUserRepository {

    @Resource
    private ApplicationOfUserMapper applicationOfUserMapper;
    @Resource
    private ApplicationOfUser2DOConvector applicationOfUser2DOConvector;

    public void create(ApplicationOfUser applicationOfUser){
            applicationOfUserMapper.insert(applicationOfUser2DOConvector.toDO(applicationOfUser));
    }

    public void update(ApplicationOfUser applicationOfUser){
            applicationOfUserMapper.updateById(applicationOfUser2DOConvector.toDO(applicationOfUser));
    }

    public ApplicationOfUser getById(Long id){
        return applicationOfUser2DOConvector.toDomain(applicationOfUserMapper.selectById(id));
    }

    @Override
    public List<ApplicationOfUser> list(ApplicationOfUserListQry applicationOfUserListQry) {
        return applicationOfUser2DOConvector.toDomainList(applicationOfUserMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<ApplicationOfUser> page(ApplicationOfUserPageQry qry) {
        QueryWrapper<ApplicationOfUserDO> qw = new QueryWrapper<>();
        IPage doPage = applicationOfUserMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),applicationOfUser2DOConvector.toDomainList(doPage.getRecords()));
    }
}
