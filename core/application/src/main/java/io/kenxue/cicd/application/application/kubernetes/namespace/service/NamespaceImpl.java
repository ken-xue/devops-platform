package io.kenxue.cicd.application.application.kubernetes.namespace.service;

import io.kenxue.cicd.application.application.kubernetes.namespace.command.NamespaceAddCmdExe;
import io.kenxue.cicd.application.application.kubernetes.namespace.command.query.NamespaceListQryExe;
import io.kenxue.cicd.coreclient.api.kubernetes.NamespaceService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceAddCmd;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public Response list(NamespaceListQry namespaceListQry) {
        return namespaceListQryExe.list(namespaceListQry);
    }

    @Override
    public Response add(NamespaceAddCmd namespaceAddCmd) {
        return namespaceAddCmdExe.add(namespaceAddCmd);
    }
}
