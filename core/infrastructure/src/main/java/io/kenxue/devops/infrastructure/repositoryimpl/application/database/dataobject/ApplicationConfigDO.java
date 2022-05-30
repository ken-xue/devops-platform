package io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
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
