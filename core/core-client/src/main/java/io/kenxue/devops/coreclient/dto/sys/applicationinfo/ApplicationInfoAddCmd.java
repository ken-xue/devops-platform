package io.kenxue.devops.coreclient.dto.sys.applicationinfo;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 应用
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationInfoAddCmd extends CommonCommand {
    @NotNull
    private ApplicationInfoDTO applicationInfoDTO;
}
