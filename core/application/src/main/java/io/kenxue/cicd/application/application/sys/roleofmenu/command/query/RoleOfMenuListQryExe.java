package io.kenxue.cicd.application.application.sys.roleofmenu.command.query;

import io.kenxue.cicd.application.application.sys.roleofmenu.assembler.RoleOfMenu2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuDTO;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuListQry;
import io.kenxue.cicd.domain.domain.sys.RoleOfMenu;
import io.kenxue.cicd.domain.repository.sys.RoleOfMenuRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Component
public class RoleOfMenuListQryExe {

    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;
    @Resource
    private RoleOfMenu2DTOAssembler roleOfMenu2DTOAssembler;

    public MultiResponse<RoleOfMenuDTO> execute(RoleOfMenuListQry qry) {
        List<RoleOfMenu> roleOfMenuList = roleOfMenuRepository.list(qry);
        return MultiResponse.of(roleOfMenu2DTOAssembler.toDTOList(roleOfMenuList));
    }

}

