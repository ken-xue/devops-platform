package io.kenxue.cicd.application.application.machine.command;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.kenxue.cicd.application.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoAddCmd;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.domain.application.MachineInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

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
            session.setTimeout(5000); // 设置timeout时间
            session.connect(); // 通过Session建立链接
        } catch (JSchException e) {
            e.printStackTrace();
            return Response.error("连接失败:"+e.getMessage());
        }

        return Response.success();
    }
}
