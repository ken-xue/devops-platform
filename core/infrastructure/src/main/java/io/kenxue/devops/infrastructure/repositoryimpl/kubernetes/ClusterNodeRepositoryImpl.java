package io.kenxue.devops.infrastructure.repositoryimpl.kubernetes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeListQry;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodePageQry;
import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
import io.kenxue.devops.domain.repository.kubernetes.ClusterNodeRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.convertor.ClusterNode2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterNodeDO;
import io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.mapper.ClusterNodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Slf4j
@Component
public class ClusterNodeRepositoryImpl implements ClusterNodeRepository {

    @Resource
    private ClusterNodeMapper clusterNodeMapper;
    @Resource
    private ClusterNode2DOConvector clusterNode2DOConvector;

    public void create(ClusterNode clusterNode){
            clusterNodeMapper.insert(clusterNode2DOConvector.toDO(clusterNode));
    }

    public void update(ClusterNode clusterNode){
            clusterNodeMapper.updateById(clusterNode2DOConvector.toDO(clusterNode));
    }

    public ClusterNode getById(Long id){
        return clusterNode2DOConvector.toDomain(clusterNodeMapper.selectById(id));
    }

    @Override
    public List<ClusterNode> list(ClusterNodeListQry clusterNodeListQry) {
        QueryWrapper<ClusterNodeDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        return clusterNode2DOConvector.toDomainList(clusterNodeMapper.selectList(qw));
    }

    @Override
    public Page<ClusterNode> page(ClusterNodePageQry qry) {
        QueryWrapper<ClusterNodeDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = clusterNodeMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),clusterNode2DOConvector.toDomainList(doPage.getRecords()));
    }
}
