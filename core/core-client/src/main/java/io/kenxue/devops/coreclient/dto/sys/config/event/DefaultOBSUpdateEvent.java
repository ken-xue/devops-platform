package io.kenxue.devops.coreclient.dto.sys.config.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Data
@AllArgsConstructor
public class DefaultOBSUpdateEvent implements DomainEventI {
    private ConfigDTO configDTO;
}
