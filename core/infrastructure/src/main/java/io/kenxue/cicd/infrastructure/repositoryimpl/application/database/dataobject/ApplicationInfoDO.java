package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@TableName("application_info")
public class ApplicationInfoDO extends CommonEntity {
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用类型
     */
    private String applicationType;
}
