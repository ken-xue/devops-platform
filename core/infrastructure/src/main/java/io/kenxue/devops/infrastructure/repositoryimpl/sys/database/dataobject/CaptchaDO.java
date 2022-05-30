package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * UserDO
 *
 * @date 2019-02-27 5:00 PM
 */
@Data
@TableName("sys_captcha")
public class CaptchaDO {
    @TableId
    public String uuid;
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private Date expireTime;
}
