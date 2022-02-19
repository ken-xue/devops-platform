package io.kenxue.cicd.infrastructure.repositoryimpl.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserListQry;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserPageQry;
import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import io.kenxue.cicd.domain.repository.project.ProjectOfUserRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.convertor.ProjectOfUser2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.dataobject.ProjectOfUserDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.mapper.ProjectOfUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Slf4j
@Component
public class ProjectOfUserRepositoryImpl implements ProjectOfUserRepository {

    @Resource
    private ProjectOfUserMapper projectOfUserMapper;
    @Resource
    private ProjectOfUser2DOConvector projectOfUser2DOConvector;

    public void create(ProjectOfUser projectOfUser){
            projectOfUserMapper.insert(projectOfUser2DOConvector.toDO(projectOfUser));
    }

    public void update(ProjectOfUser projectOfUser){
            projectOfUserMapper.updateById(projectOfUser2DOConvector.toDO(projectOfUser));
    }

    public ProjectOfUser getById(Long id){
        return projectOfUser2DOConvector.toDomain(projectOfUserMapper.selectById(id));
    }

    @Override
    public List<ProjectOfUser> list(ProjectOfUserListQry projectOfUserListQry) {
        return projectOfUser2DOConvector.toDomainList(projectOfUserMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<ProjectOfUser> page(ProjectOfUserPageQry qry) {
        QueryWrapper<ProjectOfUserDO> qw = new QueryWrapper<>();
        IPage doPage = projectOfUserMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),projectOfUser2DOConvector.toDomainList(doPage.getRecords()));
    }
}
