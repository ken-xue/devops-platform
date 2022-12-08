package io.kenxue.devops.application.kubernetes.namespace.command;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceDeleteCmd;
import io.kenxue.devops.domain.repository.kubernetes.NamespaceRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/16/15:33
 * @Description:
 */
@Component
public class NamespaceDeleteCmdExe {

    @Resource
    private NamespaceRepository namespaceRepository;

    public Response delete(NamespaceDeleteCmd namespaceDeleteCmd) {
        namespaceRepository.delete(namespaceDeleteCmd.getNamespace());
        return Response.success();
    }
}
