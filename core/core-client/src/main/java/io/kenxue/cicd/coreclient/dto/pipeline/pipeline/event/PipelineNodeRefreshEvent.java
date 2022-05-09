package io.kenxue.cicd.coreclient.dto.pipeline.pipeline.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import io.kenxue.cicd.sharedataboject.pipeline.enums.NodeExecuteStatus;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * 流水线节点状态更新时间
 *
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class PipelineNodeRefreshEvent implements DomainEventI {

    private String uuid;
    private Nodes data;
    private List<String> edges = Collections.emptyList();//激活或者执行完成的边

    public PipelineNodeRefreshEvent(String uuid, Nodes nodes, Map<String, List<String>> sourceLineMap, NodeExecuteStatus status) {
        //推送节点状态和所有输入的边
        Set<String> set = new HashSet<>();
        if (Objects.nonNull(nodes.getPoints()))
            for (String target : nodes.getPoints().getTargets()) {//当前节点作为target的所有输入路线
                String targetUUID = target.replace("target-", "");
                List<String> list = sourceLineMap.get(targetUUID);
                //重新构建line
                if (Objects.nonNull(list) && list.size() > 0)
                    for (String source : list) {
                        String line = "source-" + source + "&&" + target;
                        set.add(line);
                    }
            }
        if (set.size()>0) edges = new ArrayList<>(set);
        this.uuid = uuid;
        this.data = nodes;
    }

    public PipelineNodeRefreshEvent(String uuid, Nodes nodes, Map<String, List<String>> targetLineMap){
        Set<String> set = new HashSet<>();
        List<String> sources = nodes.getPoints().getSources();
        if (Objects.nonNull(sources) && sources.size() > 0) {
            String source = sources.get(0).replace("source-", "");
            List<String> list = targetLineMap.get(source);
            if (Objects.nonNull(list) && list.size() > 0)
                for (String target:list){
                    String line = "source-"+source+"&&"+target;
                    set.add(line);
                }
        }
        if (Objects.nonNull(set)) edges = new ArrayList<>(set);
        this.uuid = uuid;
        this.data = nodes;
    }
}
