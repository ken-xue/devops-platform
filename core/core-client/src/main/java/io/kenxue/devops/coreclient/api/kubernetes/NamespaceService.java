package io.kenxue.devops.coreclient.api.kubernetes;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceAddCmd;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceDeleteCmd;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceListQry;

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
