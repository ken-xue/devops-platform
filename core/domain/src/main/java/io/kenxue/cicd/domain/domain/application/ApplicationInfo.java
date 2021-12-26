package io.kenxue.cicd.domain.domain.application;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfo extends CommonEntity {
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用类型
     */
    private String applicationType;
}
