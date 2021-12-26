package io.kenxue.cicd.coreclient.dto.sys.applicationconfig;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationConfigPageQry extends PageQuery {

    private ApplicationConfigDTO applicationConfigDTO;

}
