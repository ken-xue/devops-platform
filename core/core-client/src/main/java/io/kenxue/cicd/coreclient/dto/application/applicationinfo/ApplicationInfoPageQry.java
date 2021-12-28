package io.kenxue.cicd.coreclient.dto.application.applicationinfo;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationInfoPageQry extends PageQuery {

    private ApplicationInfoDTO applicationInfoDTO;

}
