package io.kenxue.devops.infrastructure.repositoryimpl.middleware.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.middleware.database.dataobject.MinioDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Mapper
public interface MinioMapper extends BaseMapper<MinioDO> {

}
