package io.kenxue.devops.application.pipeline.pipeline.manager;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Session缓存管理
 *
 * @author 麦奇
 */
@Slf4j
@Service
public class SessionManager {

    private ConcurrentHashMap<String, MachineInfo> cached = new ConcurrentHashMap<>(2 << 4);

    @Resource
    private BuilderMachineManager builderMachineManager;

    /**
     * 缓存中有空闲的session则复用，没有则选择使用最少的构建服务器进行创建
     * @return
     */
    public synchronized Session get() {
        // 随机获取一台构建服务器
        MachineInfo builderMachine = builderMachineManager.getRandom();
        Session session = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(builderMachine.getAccessUsername(), builderMachine.getIp(), builderMachine.getPort());
            // 设置密码
            session.setPassword(builderMachine.getAccessPassword());
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            // 为Session对象设置properties
            session.setConfig(config);
            // 设置timeout时间
            session.setTimeout(50000);
            // 通过Session建立链接
            session.connect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return session;
        }
    }
}
