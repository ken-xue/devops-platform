package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@TableName("application_of_user")
public class ApplicationOfUserDO extends CommonEntity {
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
}
