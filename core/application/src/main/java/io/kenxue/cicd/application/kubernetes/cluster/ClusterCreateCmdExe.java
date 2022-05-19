package io.kenxue.cicd.application.kubernetes.cluster;

import io.kenxue.cicd.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.cluster.ClusterCreateCmd;
import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.domain.factory.kubernetes.ClusterFactory;
import io.kenxue.cicd.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
@Slf4j
public class ClusterCreateCmdExe {

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;
    @Resource
    private MachineInfoRepository machineInfoRepository;
    @Value("kubernetes.install.cmd")
    private String kubernetesInstallCmd;

    public Response execute(ClusterCreateCmd cmd) {

        log.info("创建kubernetes集群参数：{}",cmd);

        //1.获取master机器
        List<MachineInfo> masterMachineInfo = machineInfoRepository.getByUuid(cmd.getMasterHostList());
        //2.获取salve机器
        List<MachineInfo> slaveMachineInfo = machineInfoRepository.getByUuid(cmd.getSlaveHostList());
        //3.获取jsch连接实例安装
        masterMachineInfo.forEach(machineInfo -> {
           log.info("安装master节点:{}",machineInfo);
            // TODO: 2022/5/19

        });

        slaveMachineInfo.forEach(machineInfo -> {
            log.info("安装slave节点:{}",machineInfo);
            // TODO: 2022/5/19
        });
        //4.保存集群记录
        Cluster cluster = ClusterFactory.getCluster();
        cluster.create(UserThreadContext.get());

        clusterRepository.create(cluster);

        return Response.success();
    }
}
