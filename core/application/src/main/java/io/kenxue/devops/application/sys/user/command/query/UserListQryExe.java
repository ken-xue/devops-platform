package io.kenxue.devops.application.sys.user.command.query;

import io.kenxue.devops.application.sys.user.assembler.User2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.sys.user.UserDTO;
import io.kenxue.devops.coreclient.dto.sys.user.UserListQry;
import io.kenxue.devops.domain.domain.sys.User;
import io.kenxue.devops.domain.repository.sys.UserRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

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
        List<User> list = userRepository.list(qry);
        list.forEach(v->v.setUserPassword(""));
        return MultiResponse.of(user2DTOAssembler.toDTOList(list));
    }

}

