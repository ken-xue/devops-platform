package io.kenxue.devops.coreclient.dto.middleware.jenkins;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Data
@Accessors(chain = true)
public class JenkinsPageQry extends PageQuery {

    private JenkinsDTO jenkinsDTO;

}
