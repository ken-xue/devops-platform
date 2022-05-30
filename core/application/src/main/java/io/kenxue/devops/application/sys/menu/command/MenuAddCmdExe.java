package io.kenxue.devops.application.sys.menu.command;

import io.kenxue.devops.application.sys.menu.assembler.Menu2DTOAssembler;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.domain.domain.sys.Menu;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.menu.MenuAddCmd;
import io.kenxue.devops.domain.repository.sys.MenuRepository;
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
