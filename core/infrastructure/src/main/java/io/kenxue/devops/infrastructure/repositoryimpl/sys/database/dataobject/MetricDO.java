package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject;

import lombok.Data;

@Data
public class MetricDO extends BaseDO{

    private String id;
    /**
     * 域账号
     */
    private String userId;
    /**
     * 主度量
     */
    private String mainMetric;
    /**
     * 子度量
     */
    private String subMetric;
    /**
     * 度量条目
     */
    private String metricItem;
}
