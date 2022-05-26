package io.kenxue.cicd.coreclient.dto.middleware.minio;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Data
@Accessors(chain = true)
public class MinioPageQry extends PageQuery {

    private MinioDTO minioDTO;

}
