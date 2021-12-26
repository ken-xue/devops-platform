package io.kenxue.cicd.coreclient.dto.application.applicationconfig;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@Accessors(chain = true)
public class ApplicationConfigPageQry extends PageQuery {

    private ApplicationConfigDTO applicationConfigDTO;

}
