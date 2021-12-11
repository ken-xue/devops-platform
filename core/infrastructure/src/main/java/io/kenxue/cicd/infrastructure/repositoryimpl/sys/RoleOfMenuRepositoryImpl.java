package io.kenxue.cicd.infrastructure.repositoryimpl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuListQry;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuPageQry;
import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Menu2DOConvector;
import io.kenxue.cicd.domain.domain.sys.RoleOfMenu;
import io.kenxue.cicd.domain.repository.sys.RoleOfMenuRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.RoleOfMenu2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.MenuDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.RoleOfMenuDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper.MenuMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper.RoleOfMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Component
@Slf4j
public class RoleOfMenuRepositoryImpl implements RoleOfMenuRepository {

    @Resource
    private RoleOfMenuMapper roleOfMenuMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private Menu2DOConvector menu2DOConvector;
    @Resource
    private RoleOfMenu2DOConvector roleOfMenu2DOConvector;



    public void create(RoleOfMenu roleOfMenu){
        roleOfMenuMapper.insert(roleOfMenu2DOConvector.toDO(roleOfMenu));
    }

    public void update(RoleOfMenu roleOfMenu){
        roleOfMenuMapper.updateById(roleOfMenu2DOConvector.toDO(roleOfMenu));
    }
    
    public RoleOfMenu getById(long id){
        RoleOfMenuDO roleOfMenuDO = roleOfMenuMapper.selectById(id);
        RoleOfMenu roleOfMenu = new RoleOfMenu();
        BeanUtils.copyProperties(roleOfMenuDO, roleOfMenu);
        return roleOfMenu;
    }

    
    public Set<Menu> getMenuByRoleUuids(List<String> roleUuids) {
        Set<Menu> menus = new HashSet<>(1<<8);
        if (CollectionUtils.isEmpty(roleUuids))return menus;
        //查询关联表
        QueryWrapper<RoleOfMenuDO> rqw = new QueryWrapper<>();
        rqw.in("role_uuid",roleUuids);
        rqw.eq("deleted",0);
        List<RoleOfMenuDO> roleOfMenuDOS = roleOfMenuMapper.selectList(rqw);
        //查询菜单表
        QueryWrapper<MenuDO> mqw = new QueryWrapper<>();
        mqw.in("uuid", roleOfMenuDOS.stream().map(v -> v.getMenuUuid()).collect(Collectors.toList()));
        mqw.eq("deleted",0);
        List<MenuDO> menuDOS = menuMapper.selectList(mqw);
        menuDOS.forEach(v->menus.add(menu2DOConvector.toDomain(v)));
        return menus;
    }

    
    public List<RoleOfMenu> getRoleOfMenu(String roleUuid) {
        List<RoleOfMenu> list = new ArrayList<>(1<<8);
        QueryWrapper<RoleOfMenuDO> rqw = new QueryWrapper<>();
        rqw.eq("role_uuid",roleUuid);
        rqw.eq("deleted",0);
        roleOfMenuMapper.selectList(rqw).forEach(v-> list.add(roleOfMenu2DOConvector.toDomain(v)));
        return list;
    }

    
    public void deleteByRoleUuid(String roleUuid) {
        QueryWrapper<RoleOfMenuDO> rqw = new QueryWrapper<>();
        rqw.eq("role_uuid",roleUuid);
        this.roleOfMenuMapper.delete(rqw);
    }

    @Override
    public List<RoleOfMenu> list(RoleOfMenuListQry qry) {
        return roleOfMenu2DOConvector.toDomainList(roleOfMenuMapper.selectList(new QueryWrapper<>()));}

    @Override
    public Page<RoleOfMenu> page(RoleOfMenuPageQry qry) {
        QueryWrapper<RoleOfMenuDO> wp = new QueryWrapper<>();
        wp.eq("deleted",0);//未删除
        IPage doPage = new PageDTO(qry.getPageIndex(), qry.getPageSize());
        IPage<RoleOfMenuDO> page = roleOfMenuMapper.selectPage(doPage, wp);
        return Page.of(page.getCurrent(),page.getSize(),page.getTotal(),roleOfMenu2DOConvector.toDomainList(page.getRecords()));
    }
}
