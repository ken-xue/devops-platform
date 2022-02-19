package io.kenxue.cicd.infrastructure.repositoryimpl.project.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@TableName("project_of_user")
public class ProjectOfUserDO extends CommonEntity {
    /**
     * 项目uuid
     */
    private String projectUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
}
