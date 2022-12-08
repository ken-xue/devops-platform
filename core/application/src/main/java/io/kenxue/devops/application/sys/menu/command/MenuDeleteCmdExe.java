package io.kenxue.devops.application.sys.menu.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.domain.domain.sys.Menu;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.menu.MenuDeleteCmd;
import io.kenxue.devops.domain.repository.sys.MenuRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.stream.Stream;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
public class MenuDeleteCmdExe {
    @Resource
    private MenuRepository menuRepository;

    public Response execute(MenuDeleteCmd cmd) {
        Stream.of(cmd.getIds()).forEach(id->{
            Menu menu = menuRepository.getById(id);
            menu.deleted(UserThreadContext.get());
            menuRepository.update(menu);
        });
        return Response.success();
    }
}
