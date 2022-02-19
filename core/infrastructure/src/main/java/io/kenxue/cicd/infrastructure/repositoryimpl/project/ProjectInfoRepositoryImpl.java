package io.kenxue.cicd.infrastructure.repositoryimpl.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoListQry;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.ProjectInfoPageQry;
import io.kenxue.cicd.coreclient.dto.sys.user.UserDTO;
import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import io.kenxue.cicd.domain.repository.project.ProjectInfoRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.convertor.ProjectInfo2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.dataobject.ProjectInfoDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.mapper.ProjectInfoMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.UserDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper.UserMapper;
import io.kenxue.cicd.sharedataboject.project.enums.ProjectTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Slf4j
@Component
public class ProjectInfoRepositoryImpl implements ProjectInfoRepository {

    @Resource
    private ProjectInfoMapper projectInfoMapper;
    @Resource
    private ProjectInfo2DOConvector projectInfo2DOConvector;
    @Resource
    private UserMapper userMapper;

    public void create(ProjectInfo projectInfo){
            projectInfoMapper.insert(projectInfo2DOConvector.toDO(projectInfo));
    }

    public void update(ProjectInfo projectInfo){
            projectInfoMapper.updateById(projectInfo2DOConvector.toDO(projectInfo));
    }

    public ProjectInfo getById(Long id){
        return projectInfo2DOConvector.toDomain(projectInfoMapper.selectById(id));
    }

    @Override
    public List<ProjectInfo> list(ProjectInfoListQry projectInfoListQry) {
        return projectInfo2DOConvector.toDomainList(projectInfoMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<ProjectInfo> page(ProjectInfoPageQry qry) {
        QueryWrapper<ProjectInfoDO> qw = new QueryWrapper<>();
        UserDTO user = UserThreadContext.getUser();
        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>().eq("user_id", user.getUserId()));
        String uuid = userDO.getUuid();
        qw.eq("user_uuid",uuid);
        ProjectTypeEnum projectType = qry.getProjectType();
        IPage<ProjectInfoDO> doPage;
        PageDTO pageDTO = new PageDTO(qry.getPageIndex(), qry.getPageSize());
        if(projectType==ProjectTypeEnum.JOIN){
            doPage = projectInfoMapper.queryRelatedProject(pageDTO,userDO.getUuid(),qry.getProjectInfoDTO().getUsername(),qry.getProjectInfoDTO().getProjectName());
        }else {
            if (Objects.nonNull(qry.getProjectInfoDTO())&&
                    StringUtils.isNotBlank(qry.getProjectInfoDTO().getProjectName()))
                qw.like("project_name",qry.getProjectInfoDTO().getProjectName());
            doPage = projectInfoMapper.selectPage(pageDTO, qw);
            doPage.getRecords().forEach(v-> v.setUsername(userDO.getUserName()));
        }
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),projectInfo2DOConvector.toDomainList(doPage.getRecords()));
    }
}
