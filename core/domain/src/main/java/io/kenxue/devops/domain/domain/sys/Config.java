package io.kenxue.devops.domain.domain.sys;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config extends CommonEntity {
    /**
     * 名字
     */
    private String name;
    /**
     * 配置信息
     */
    private String config;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否可删除
     */
    private Boolean deletable;

    private Boolean editable;
}
