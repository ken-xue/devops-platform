package io.kenxue.devops.sharedataboject.pipeline.context;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author biaoyang
 */
@Data
@Accessors(chain = true)
public class ExecuteContext {

    private Map<String,Object> attributes = new HashMap<>(2<<4);

    public Object getAttributes(String name){
        return attributes.get(name);
    }

    public void setAttributes(String name,Object value){
        attributes.put(name,value);
    }
}
