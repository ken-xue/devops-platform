package io.kenxue.cicd.application.application.sys.menu.command.query;

import io.kenxue.cicd.application.application.sys.menu.assembler.Menu2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuDTO;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuPageQry;
import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.domain.repository.sys.MenuRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
public class MenuPageQryExe {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private Menu2DTOAssembler menu2DTOAssembler;

    /**
     * 查询菜单分页
     * @param qry
     * @return
     */
    public PageResponse<MenuDTO> execute(MenuPageQry qry) {
        Page<Menu> page = menuRepository.page(qry);
        //设置是否有子节点
        page.getRecords().forEach(v-> v.setHasChildren(!CollectionUtils.isEmpty(menuRepository.getByParentUuid(v.getUuid()))));
        return PageResponse.of(menu2DTOAssembler.toDTOPage(page));
    }

}

