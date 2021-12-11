package io.kenxue.cicd.acl.event;

import java.util.Map;

/**
 * 跨应用事件通知,生产事件
 */
public interface Producer {
    void produce(String messageKey, byte[] message, Map<String, String> attributes) throws Exception;
}
