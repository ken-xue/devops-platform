package io.kenxue.cicd.acl.impl.incident;

import io.kenxue.cicd.acl.event.Producer;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * the default event producer for another application
 */
@Service
public class EventProducerImpl implements Producer {
    @Override
    public void produce(String messageKey, byte[] message, Map<String, String> attributes) throws Exception {
        //TODO:produce app
    }
}
