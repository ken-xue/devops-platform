package io.kenxue.devops.application.machine.command;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.kenxue.devops.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoAddCmd;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

import static io.kenxue.devops.coreclient.exception.code.SSHErrorCode.CONNECTION_ERROR;

/**
 * 服务器节点测试连接
 *
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineTestConnCmdExe {

    @Resource
    private MachineInfo2DTOAssembler machineInfo2DTOAssembler;

    public Response execute(MachineInfoAddCmd cmd) {

        MachineInfo machineInfo = machineInfo2DTOAssembler.toDomain(cmd.getMachineInfoDTO());

        JSch jsch = new JSch();

        String ip = machineInfo.getIp();
        Integer port = machineInfo.getPort();
        String accessUsername = machineInfo.getAccessUsername();

        try {
            Session session = jsch.getSession(accessUsername, ip, port);
            session.setPassword(machineInfo.getAccessPassword()); // 设置密码
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(4000); // 设置timeout时间
            session.connect(); // 通过Session建立链接
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(CONNECTION_ERROR.getCode(),CONNECTION_ERROR.getDesc()+e.getMessage());
        }
        return Response.success();
    }
}
