package io.kenxue.cicd.coreclient.dto.sys.log;


import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Data
@Accessors(chain = true)
public class LogDTO extends CommonDTO {
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
