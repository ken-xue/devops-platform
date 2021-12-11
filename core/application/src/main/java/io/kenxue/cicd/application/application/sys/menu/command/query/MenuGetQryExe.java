package io.kenxue.cicd.application.application.sys.menu.command.query;

import io.kenxue.cicd.application.application.sys.menu.assembler.Menu2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuDTO;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuGetQry;
import io.kenxue.cicd.domain.repository.sys.MenuRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
public class MenuGetQryExe {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private Menu2DTOAssembler menu2DTOAssembler;

    public SingleResponse<MenuDTO> execute(MenuGetQry qry) {
        return SingleResponse.of(menu2DTOAssembler.toDTO(menuRepository.getById(qry.getId())));
    }

}
