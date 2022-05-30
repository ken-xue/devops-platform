package io.kenxue.devops.application.common.loader;

import java.util.List;

public interface Loader<K,V> {
    V load();
    V load(K key);
    List<V> loadAll();
    List<V> loadAll(List<K> keys);
}
