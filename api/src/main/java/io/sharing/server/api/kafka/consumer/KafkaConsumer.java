package io.sharing.server.api.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic", groupId = "group_1")
    public void listener(Object data) {
        System.out.println(data);
    }

}
