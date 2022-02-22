package io.kenxue.cicd.application.application.kubernetes.namespace.command;

import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceDeleteCmd;
import io.kenxue.cicd.domain.repository.kubernetes.NamespaceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
