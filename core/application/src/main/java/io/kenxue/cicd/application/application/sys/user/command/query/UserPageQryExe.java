package io.kenxue.cicd.application.application.sys.user.command.query;

import io.kenxue.cicd.application.application.sys.user.assembler.User2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.user.UserDTO;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.sys.user.UserPageQry;
import io.kenxue.cicd.domain.domain.sys.User;
import io.kenxue.cicd.domain.repository.sys.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserPageQryExe {
    @Resource
    private User2DTOAssembler user2DTOAssembler;
    @Resource
    private UserRepository userRepository;

    public PageResponse<UserDTO> execute(UserPageQry qry) {
        Page<User> page = userRepository.page(qry);
        return PageResponse.of(user2DTOAssembler.toDTOPage(page));}

}

