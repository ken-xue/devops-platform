package io.kenxue.cicd.application.application.sys.menu.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuDeleteCmd;
import io.kenxue.cicd.domain.repository.sys.MenuRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
