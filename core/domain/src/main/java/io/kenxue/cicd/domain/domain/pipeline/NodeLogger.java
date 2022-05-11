package io.kenxue.cicd.domain.domain.pipeline;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NodeLogger extends CommonEntity {
    /**
     * 流水线执行记录uuid
     */
    private String loggerUuid;
    /**
     * 节点uuid
     */
    private String nodeUuid;
    /**
     * 存储数据正文
     */
    private String logger;
    /**
     * 执行时间
     */
    private Date executeStartTime;
    /**
     * 结束时间
     */
    private Date executeEndTime;
    /**
     * 存储日志
     */
    private StringBuilder sb = new StringBuilder();
    /**
     * 序列化
     */
    public void serializable(){
        logger = sb.toString();
    }

    public void append(String logger){
        sb.append(logger);
    }
}
