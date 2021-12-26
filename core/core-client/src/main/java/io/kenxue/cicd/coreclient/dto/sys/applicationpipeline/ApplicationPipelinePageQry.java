package io.kenxue.cicd.coreclient.dto.sys.applicationpipeline;

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
public class ApplicationPipelinePageQry extends PageQuery {

    private ApplicationPipelineDTO applicationPipelineDTO;

}
