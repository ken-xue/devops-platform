package io.kenxue.cicd.application.application.sys.menu.command;

import io.kenxue.cicd.application.application.sys.menu.assembler.Menu2DTOAssembler;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuAddCmd;
import io.kenxue.cicd.domain.repository.sys.MenuRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
public class MenuAddCmdExe {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private Menu2DTOAssembler menu2DTOAssembler;

    public Response execute(MenuAddCmd cmd) {
        Menu menu = menu2DTOAssembler.toDomain(cmd.getMenuDTO());
        menu.create(UserThreadContext.get());
        menuRepository.create(menu);
        return Response.success();
    }
}
