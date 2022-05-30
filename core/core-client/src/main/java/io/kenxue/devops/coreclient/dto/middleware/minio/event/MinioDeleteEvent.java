package io.kenxue.devops.coreclient.dto.middleware.minio.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Data
@AllArgsConstructor
public class MinioDeleteEvent implements DomainEventI {
    private String uuid;
}
