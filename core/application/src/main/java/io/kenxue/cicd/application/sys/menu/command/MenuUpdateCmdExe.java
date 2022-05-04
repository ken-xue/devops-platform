package io.kenxue.cicd.application.sys.menu.command;

import io.kenxue.cicd.application.sys.menu.assembler.Menu2DTOAssembler;
import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuUpdateCmd;
import io.kenxue.cicd.domain.repository.sys.MenuRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
public class MenuUpdateCmdExe {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private Menu2DTOAssembler menu2DTOAssembler;

    public Response execute(MenuUpdateCmd cmd) {
        Menu menu = menu2DTOAssembler.toDomain(cmd.getMenuDTO());
        menuRepository.update(menu);
        return Response.success();
    }
}