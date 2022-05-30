package io.kenxue.devops.coreclient.dto.sys.applicationconfig;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationConfigUpdateCmd extends CommonCommand {

    @NotNull
    private ApplicationConfigDTO applicationConfigDTO;
}
