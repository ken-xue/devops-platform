package io.kenxue.cicd.coreclient.api.kubernetes;

import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceAddCmd;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceDeleteCmd;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceListQry;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/14:31
 * @Description:
 */
public interface NamespaceService {

    Response list(NamespaceListQry namespaceListQry);

    Response add(NamespaceAddCmd namespaceAddCmd);

    Response delete(NamespaceDeleteCmd namespaceDeleteCmd);
}
