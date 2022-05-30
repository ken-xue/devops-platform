package io.kenxue.devops.coreclient.dto.middleware.minio;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Data
@Accessors(chain = true)
public class MinioDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
