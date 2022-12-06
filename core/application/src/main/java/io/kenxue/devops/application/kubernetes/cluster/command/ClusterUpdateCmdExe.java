package io.kenxue.devops.application.kubernetes.cluster.command;

import io.kenxue.devops.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.devops.domain.domain.kubernetes.Cluster;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterUpdateCmd;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.devops.domain.repository.machine.MachineInfoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

import static io.kenxue.devops.coreclient.exception.code.SSHErrorCode.CONNECTION_ERROR;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterUpdateCmdExe {

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;
    @Resource
    private MachineInfoRepository machineInfoRepository;

    public Response execute(ClusterUpdateCmd cmd) {
        Cluster cluster = cluster2DTOAssembler.toDomain(cmd.getClusterDTO());
        if (StringUtils.isNotBlank(cluster.getMachineUuid())){
            MachineInfo machineInfo = machineInfoRepository.getByUuid(cluster.getMachineUuid());
            try {
                machineInfo.testConnection();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error(CONNECTION_ERROR.getCode(),CONNECTION_ERROR.getDesc()+e.getMessage());
            }
        }
        clusterRepository.update(cluster);
        return Response.success();
    }
}