package io.sharing.server.api.kafka.outbox;

import io.sharing.server.api.kafka.producer.KafkaProducer;
import io.sharing.server.core.outbox.application.service.OutboxService;
import io.sharing.server.core.outbox.domain.Outbox;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record OutboxRetryTask(OutboxService outboxService, KafkaProducer producer) {

    @Scheduled(fixedDelayString = "10000") // 10초 마다 호출
    public void retry() {

        List<Outbox> outboxes = outboxService.findAll();

        outboxes.stream().forEach(e -> {
            producer.create(e.getTopicName(), e.getMessage());
            outboxService.deleteByEntity(e);
        });
    }
}
