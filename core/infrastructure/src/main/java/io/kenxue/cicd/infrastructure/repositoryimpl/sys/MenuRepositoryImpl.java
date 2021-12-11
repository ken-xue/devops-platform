package io.kenxue.cicd.infrastructure.repositoryimpl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuGetSelectQry;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuPageQry;
import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.domain.repository.sys.MenuRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Menu2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.MenuDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
@Slf4j
public class MenuRepositoryImpl implements MenuRepository {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private Menu2DOConvector menu2DOConvector;

    public void create(Menu menu){
        menuMapper.insert(menu2DOConvector.toDO(menu));
    }

    public void update(Menu menu){
        menuMapper.updateById(menu2DOConvector.toDO(menu));
    }
    
    public Menu getById(Long id){
        MenuDO menuDO = menuMapper.selectById(id);
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDO, menu);
        return menu;
    }

    /**
     * 根据父id查询子菜单
     * @param uuid
     * @return
     */
    public List<Menu> getByParentUuid(String uuid) {
        List<Menu> menus = new ArrayList<>();
        QueryWrapper<MenuDO> wp = new QueryWrapper<>();
        wp.eq("deleted",0);//未删除
        wp.eq("menu_parent_uuid",uuid);
        menuMapper.selectList(wp).forEach(v-> menus.add(menu2DOConvector.toDomain(v)));
        return menus;
    }

    /**
     * 根据父id查询子菜单
     * @param menuParentUuid
     * @return
     */
    @Override
    public List<Menu> list(String menuParentUuid) {
        QueryWrapper<MenuDO> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0);//未删除
        //根据父id查询子菜单
        if (StringUtils.isNotBlank(menuParentUuid)){
            wrapper.eq("menu_parent_uuid",menuParentUuid);
        }
        List<MenuDO> menuDOList = menuMapper.selectList(wrapper);
        return menuDOList.stream().map(v->menu2DOConvector.toDomain(v)).collect(Collectors.toList());
    }

    /**
     * 分页查询
     * @param qry
     * @return
     */
    @Override
    public Page<Menu> page(MenuPageQry qry) {
        QueryWrapper<MenuDO> wp = new QueryWrapper<>();
        wp.and(wrapper -> wrapper.eq("menu_parent_uuid",0).eq("menu_type", 0).eq("deleted", 0));
        wp.or(wrapper -> wrapper.eq("deleted", 0).eq("menu_type", 0));
        wp.or(wrapper -> wrapper.isNull("menu_parent_uuid").eq("deleted", 0));
        IPage<MenuDO> doPage = new PageDTO<>(qry.getPageIndex(), qry.getPageSize());
        //名字查询
        if (Objects.nonNull(qry.getMenuCO())&&StringUtils.isNotBlank(qry.getMenuCO().getMenuName())){
            wp.and(w->w.like("menu_name",qry.getMenuCO().getMenuName()));
        }
        IPage<MenuDO> page = menuMapper.selectPage(doPage, wp);
        return Page.of(page.getCurrent(),page.getSize(),page.getTotal(),menu2DOConvector.toDomainList(page.getRecords()));
    }

    /**
     * 查询下拉框列表
     * @param qry
     * @return
     */
    @Override
    public List<Menu> listForSelect(MenuGetSelectQry qry) {
        QueryWrapper<MenuDO> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",0);//未删除
        if (StringUtils.isNotBlank(qry.getExcludeTypes())){
            Stream.of(qry.getExcludeTypes().split(",")).forEach(v->wrapper.ne("menu_type", v));
        }
        if (StringUtils.isNotBlank(qry.getIncludeTypes())){
            Stream.of(qry.getExcludeTypes().split(",")).forEach(v->wrapper.eq("menu_type", v));
        }
        return menu2DOConvector.toDomainList(menuMapper.selectList(wrapper));
    }
}
