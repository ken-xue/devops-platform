package io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
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
