package io.kenxue.cicd.domain.domain.project;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfo extends CommonEntity {
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 负责人uuid
     */
    private String userUuid;
    /**
     * 负责人username
     */
    private String username;
    /**
     * 描述
     */
    private String projectDesc;
    /**
     * 项目成员
     */
    private List<String> userMembers;
}
