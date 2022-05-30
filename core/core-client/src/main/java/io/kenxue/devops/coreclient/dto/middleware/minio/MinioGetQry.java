package io.kenxue.devops.coreclient.dto.middleware.minio;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Data
@Accessors(chain = true)
public class MinioGetQry extends CommonCommand {

    private Long id;
    @ApiModelProperty(value = "uri")
    private String uri;
    @ApiModelProperty(value = "访问用户名")
    private String username;
    @ApiModelProperty(value = "访问密码或秘钥")
    private String password;
    @ApiModelProperty(value = "别名")
    private String name;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "访问端口")
    private Integer port;
}