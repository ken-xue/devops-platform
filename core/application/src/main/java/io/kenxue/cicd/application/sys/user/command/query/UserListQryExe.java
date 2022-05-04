package io.kenxue.cicd.application.sys.user.command.query;

import io.kenxue.cicd.application.sys.user.assembler.User2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.sys.user.UserDTO;
import io.kenxue.cicd.coreclient.dto.sys.user.UserListQry;
import io.kenxue.cicd.domain.repository.sys.UserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 *
 */
@Component
public class UserListQryExe {

    @Resource
    private UserRepository userRepository;
    @Resource
    private User2DTOAssembler user2DTOAssembler;

    public MultiResponse<UserDTO> execute(UserListQry qry) {
        return MultiResponse.of(user2DTOAssembler.toDTOList(userRepository.list(qry)));
    }

}

