package io.kenxue.cicd.coreclient.dto.application.applicationconfig;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@Accessors(chain = true)
public class ApplicationConfigDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
