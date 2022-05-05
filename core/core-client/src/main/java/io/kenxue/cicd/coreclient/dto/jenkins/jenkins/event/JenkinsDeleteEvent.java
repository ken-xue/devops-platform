package io.kenxue.cicd.coreclient.dto.jenkins.jenkins.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Data
@AllArgsConstructor
public class JenkinsDeleteEvent implements DomainEventI {
    private String uuid;
}