package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Data
@TableName("sys_log")
public class LogDO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 执行时长(毫秒)
     */
    private Long executeTime;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 发生时间
     */
    private Date occurTime;
}
