package io.kenxue.devops.coreclient.dto.sys.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Data
@Accessors(chain = true)
public class ConfigUpdateCmd extends CommonCommand {

    @NotNull
    private ConfigDTO configDTO;
}
