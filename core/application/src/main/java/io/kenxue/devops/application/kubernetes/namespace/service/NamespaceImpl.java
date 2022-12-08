package io.kenxue.devops.application.kubernetes.namespace.service;

import io.kenxue.devops.application.kubernetes.namespace.command.NamespaceAddCmdExe;
import io.kenxue.devops.application.kubernetes.namespace.command.NamespaceDeleteCmdExe;
import io.kenxue.devops.application.kubernetes.namespace.command.query.NamespaceListQryExe;
import io.kenxue.devops.coreclient.api.kubernetes.NamespaceService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceAddCmd;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceDeleteCmd;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/14:39
 * @Description:
 */
@Service
public class NamespaceImpl implements NamespaceService {

    @Resource
    private NamespaceListQryExe namespaceListQryExe;
    @Resource
    private NamespaceAddCmdExe namespaceAddCmdExe;
    @Resource
    private NamespaceDeleteCmdExe namespaceDeleteCmdExe;

    @Override
    public Response list(NamespaceListQry namespaceListQry) {
        return namespaceListQryExe.list(namespaceListQry);
    }

    @Override
    public Response add(NamespaceAddCmd namespaceAddCmd) {
        return namespaceAddCmdExe.add(namespaceAddCmd);
    }

    @Override
    public Response delete(NamespaceDeleteCmd namespaceDeleteCmd) {
        return namespaceDeleteCmdExe.delete(namespaceDeleteCmd);
    }
}
