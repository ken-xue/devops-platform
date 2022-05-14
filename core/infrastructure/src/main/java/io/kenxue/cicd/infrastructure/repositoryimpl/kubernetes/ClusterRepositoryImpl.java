package io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterListQry;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterPageQry;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.convertor.Cluster2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.dataobject.ClusterDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.mapper.ClusterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Slf4j
@Component
public class ClusterRepositoryImpl implements ClusterRepository {

    @Resource
    private ClusterMapper clusterMapper;
    @Resource
    private Cluster2DOConvector cluster2DOConvector;

    public void create(Cluster cluster){
            clusterMapper.insert(cluster2DOConvector.toDO(cluster));
    }

    public void update(Cluster cluster){
            clusterMapper.updateById(cluster2DOConvector.toDO(cluster));
    }

    public Cluster getById(Long id){
        return cluster2DOConvector.toDomain(clusterMapper.selectById(id));
    }

    @Override
    public List<Cluster> list(ClusterListQry clusterListQry) {
        QueryWrapper<ClusterDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        return cluster2DOConvector.toDomainList(clusterMapper.selectList(qw));
    }

    @Override
    public Page<Cluster> page(ClusterPageQry qry) {
        QueryWrapper<ClusterDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = clusterMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),cluster2DOConvector.toDomainList(doPage.getRecords()));
    }
}
