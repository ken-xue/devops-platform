package io.kenxue.devops.coreclient.dto.sys.config;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Data
@Accessors(chain = true)
public class ConfigDTO extends CommonDTO {
    @Schema(description = "名字")
    private String name;
    @Schema(description = "配置信息")
    private String config;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "是否可删除")
    private Boolean deletable;
    @Schema(description = "是否编辑")
    private Boolean editable;
}
