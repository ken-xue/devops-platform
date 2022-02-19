package io.kenxue.cicd.infrastructure.repositoryimpl.project.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@TableName("project_info")
public class ProjectInfoDO extends CommonEntity {
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 负责人
     */
    private String userUuid;
    /**
     * 负责人username
     */
    @TableField(exist = false)
    private String username;
    /**
     * 描述
     */
    private String projectDesc;
}
