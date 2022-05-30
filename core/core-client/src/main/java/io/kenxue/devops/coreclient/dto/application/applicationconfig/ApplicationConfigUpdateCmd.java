package io.kenxue.devops.coreclient.dto.application.applicationconfig;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationConfigUpdateCmd extends CommonCommand {

    @NotNull
    private ApplicationConfigDTO applicationConfigDTO;
}
