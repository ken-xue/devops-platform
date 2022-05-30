package io.kenxue.devops.domain.domain.sys;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOfRole extends CommonEntity {
    /**
     * 用户UUID
     */
    private String userUuid;
    /**
     * 角色UUID
     */
    private String roleUuid;

    
    public void validate() {
        Assert.notNull(userUuid,"userUuid should not be null");
        Assert.notNull(roleUuid,"roleUuid should not be null");
    }
}
