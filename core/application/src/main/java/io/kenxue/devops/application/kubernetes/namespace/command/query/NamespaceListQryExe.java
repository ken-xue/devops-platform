package io.kenxue.devops.application.kubernetes.namespace.command.query;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import io.kenxue.devops.domain.domain.kubernetes.Namespace;
import io.kenxue.devops.domain.repository.kubernetes.NamespaceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/15:55
 * @Description:
 */
@Component
public class NamespaceListQryExe {

    @Resource
    private NamespaceRepository namespaceRepository;

    public MultiResponse<Namespace> list(NamespaceListQry namespaceListQry) {
        List<Namespace> list = namespaceRepository.list(namespaceListQry);
        return MultiResponse.of(list);
    }
}
