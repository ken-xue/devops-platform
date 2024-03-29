package io.kenxue.devops.coreclient.dto.middleware.jenkins;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Data
@Accessors(chain = true)
public class JenkinsUpdateCmd extends CommonCommand {

    @NotNull
    private JenkinsDTO jenkinsDTO;
}
