package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@TableName("application_config")
public class ApplicationConfigDO extends CommonEntity {
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 应用配置
     */
    private String configContext;
}
