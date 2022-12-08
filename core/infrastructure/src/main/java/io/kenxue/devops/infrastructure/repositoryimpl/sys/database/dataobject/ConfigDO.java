package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Data
@TableName("sys_config")
public class ConfigDO extends CommonEntity {
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
    /**
     * 是否可编辑
     */
    private Boolean editable;
}
