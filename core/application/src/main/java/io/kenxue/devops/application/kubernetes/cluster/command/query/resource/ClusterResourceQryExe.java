package io.kenxue.devops.application.kubernetes.cluster.command.query.resource;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterResourceQry;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterResourceQryExe {
    @Resource
    private ApplicationContext ctx;

    public MultiResponse execute(ClusterResourceQry qry) {

        String resource = qry.getResource();

        ClusterResourceQryI exe = (ClusterResourceQryI) ctx.getBean(resource);

        if (Objects.isNull(exe))throw new RuntimeException("Function not implemented");

        MultiResponse ret = exe.execute(qry);

        return ret;
    }
}
