package io.kenxue.devops.coreclient.dto.sys.log;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Data
@Accessors(chain = true)
public class LogAddCmd extends CommonCommand {
    @NotNull
    private LogDTO logDTO;
}
