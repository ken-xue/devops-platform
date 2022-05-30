package io.kenxue.devops.sharedataboject.pipeline.context;

import java.util.List;

public interface Result {
    Object get(Object key);
    List<Object> getAll();
    void add(Object key,Object value);
}
