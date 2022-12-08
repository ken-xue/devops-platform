package io.kenxue.devops.infrastructure.repositoryimpl.middleware;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.middleware.minio.MinioListQry;
import io.kenxue.devops.domain.domain.middleware.Minio;
import io.kenxue.devops.domain.repository.middleware.MinioRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.middleware.database.convertor.Minio2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.middleware.database.dataobject.MinioDO;
import io.kenxue.devops.coreclient.dto.middleware.minio.MinioPageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.middleware.database.mapper.MinioMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Slf4j
@Component
public class MinioRepositoryImpl implements MinioRepository {

    @Resource
    private MinioMapper minioMapper;
    @Resource
    private Minio2DOConvector minio2DOConvector;

    public void create(Minio minio){
            minioMapper.insert(minio2DOConvector.toDO(minio));
    }

    public void update(Minio minio){
            minioMapper.updateById(minio2DOConvector.toDO(minio));
    }

    public Minio getById(Long id){
        return minio2DOConvector.toDomain(minioMapper.selectById(id));
    }

    @Override
    public List<Minio> list(MinioListQry minioListQry) {
        return minio2DOConvector.toDomainList(minioMapper.selectList(new QueryWrapper<MinioDO>().eq("deleted",false)));
    }

    @Override
    public Page<Minio> page(MinioPageQry qry) {
        QueryWrapper<MinioDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = minioMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),minio2DOConvector.toDomainList(doPage.getRecords()));
    }
}
