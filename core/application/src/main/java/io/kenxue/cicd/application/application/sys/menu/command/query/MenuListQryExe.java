package io.kenxue.cicd.application.application.sys.menu.command.query;

import io.kenxue.cicd.application.application.sys.menu.assembler.Menu2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuDTO;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuListQry;
import io.kenxue.cicd.domain.repository.sys.MenuRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
public class MenuListQryExe {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private Menu2DTOAssembler menu2DTOAssembler;

    public MultiResponse<MenuDTO> execute(MenuListQry qry) {

        List<MenuDTO> menuDTOList = new ArrayList<>();
        menuRepository.list(qry.getMenuParentUuid()).forEach(menu -> {
            MenuDTO menuDTO = menu2DTOAssembler.toDTO(menu);
            menuDTO.setHasChildren(!CollectionUtils.isEmpty(menuRepository.getByParentUuid(menu.getUuid())));
            menuDTOList.add(menuDTO);
        });
        return MultiResponse.of(menuDTOList);
    }
}

