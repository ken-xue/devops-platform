package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;

/**
 * 用户关联部门表
 * @author mikey
 * @date 2021-11-21 09:20:42
 */
@Data
@TableName("sys_user_of_dept")
public class UserOfDeptDO extends CommonEntity {
    /**
     * 用户UUID
     */
    @TableId
    private String userUuid;
    /**
     * 部门UUID
     */
    private String deptUuid;
}
