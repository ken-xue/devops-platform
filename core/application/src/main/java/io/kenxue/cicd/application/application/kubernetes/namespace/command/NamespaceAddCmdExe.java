package io.kenxue.cicd.application.application.kubernetes.namespace.command;

import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceAddCmd;
import io.kenxue.cicd.domain.repository.kubernetes.NamespaceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
