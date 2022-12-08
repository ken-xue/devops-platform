package io.kenxue.devops.coreclient.dto.application.applicationinfo;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationInfoUpdateCmd extends CommonCommand {

    @NotNull
    private ApplicationInfoDTO applicationInfoDTO;
}
