package io.kenxue.devops.application.pipeline.pipeline.manager;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.Session;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * channel缓存管理
 * @author 麦奇
 */
@Slf4j
@Service
public class ChannelManager {

    @Resource
    private SessionManager sessionManager;

    @SneakyThrows
    public ChannelShell getChannelShell(){
        Session session = sessionManager.get();
        ChannelShell shell = (ChannelShell) session.openChannel("shell");
        shell.setPty(true);
        shell.connect();
        return shell;
    }

}
