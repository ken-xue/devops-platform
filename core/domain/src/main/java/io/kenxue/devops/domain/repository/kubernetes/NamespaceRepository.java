package io.kenxue.devops.domain.repository.kubernetes;

import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import io.kenxue.devops.domain.domain.kubernetes.Namespace;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/16:01
 * @Description:
 */
public interface NamespaceRepository {

    List<Namespace> list(NamespaceListQry namespaceListQry);

    void add(String namespaceName);

    void delete(String namespaceName);
}
