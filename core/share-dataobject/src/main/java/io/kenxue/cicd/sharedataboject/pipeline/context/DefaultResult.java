package io.kenxue.cicd.sharedataboject.pipeline.context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DefaultResult implements Result{

    private HashMap<Object,Object> map = new HashMap<>(2<<4);

    public Object get(Object key){
        return map.get(key);
    }

    @Override
    public List<Object> getAll() {
        return Arrays.asList(map.values().toArray());
    }

    @Override
    public void add(Object key, Object value) {
        map.put(key,value);
    }
}
