package io.kenxue.cicd.domain.domain.application;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationConfig extends CommonEntity {
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 应用配置
     */
    private String configContext;
}
