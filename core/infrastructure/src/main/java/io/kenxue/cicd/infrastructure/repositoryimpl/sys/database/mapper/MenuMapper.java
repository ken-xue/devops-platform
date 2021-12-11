package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.MenuDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {

}
