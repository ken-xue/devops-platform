package io.kenxue.cicd.application.application.kubernetes.namespace.command.query;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import io.kenxue.cicd.domain.domain.kubernetes.Namespace;
import io.kenxue.cicd.domain.repository.kubernetes.NamespaceRepository;
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
