package io.kenxue.cicd.coreclient.context;


import io.kenxue.cicd.coreclient.dto.sys.user.UserDTO;

import java.util.Optional;

/**
 * @Author: 麦奇
 * @desc: 用户本地线程上下文
 * @Date: 21-11-14 上午10:13
 */
public class UserThreadContext {

    private static final ThreadLocal<UserDTO> threadLocal = new ThreadLocal();

    public UserThreadContext() {
    }

    public static void set(UserDTO userCO) {
        threadLocal.set(userCO);
    }

    public static String get() {
        return (String) Optional.ofNullable(threadLocal.get()).map(UserDTO::getUserId).orElse(null);
    }

    public static UserDTO getUser() {
        return (UserDTO)threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static ThreadLocal<UserDTO> getThreadLocal() {
        return threadLocal;
    }
}

