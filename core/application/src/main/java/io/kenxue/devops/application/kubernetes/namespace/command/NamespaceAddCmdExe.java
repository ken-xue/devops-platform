package io.kenxue.devops.application.kubernetes.namespace.command;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceAddCmd;
import io.kenxue.devops.domain.repository.kubernetes.NamespaceRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/21:15
 * @Description:
 */
@Component
public class NamespaceAddCmdExe {

    @Resource
    private NamespaceRepository namespaceRepository;

    public Response add(NamespaceAddCmd namespaceAddCmd) {
        namespaceRepository.add(namespaceAddCmd.getNamespaceName());
        return Response.success();
    }
}
