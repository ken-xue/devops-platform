package io.kenxue.devops.coreclient.dto.middleware.minio;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Data
@Accessors(chain = true)
public class MinioDTO extends CommonDTO {
    @Schema(description = "uri")
    private String uri;
    @Schema(description = "访问用户名")
    private String username;
    @Schema(description = "访问密码或秘钥")
    private String password;
    @Schema(description = "别名")
    private String name;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "访问端口")
    private Integer port;
}
