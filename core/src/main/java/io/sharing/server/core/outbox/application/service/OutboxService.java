package io.sharing.server.core.outbox.application.service;

import io.sharing.server.core.outbox.application.port.outp.OutboxRepository;
import io.sharing.server.core.outbox.domain.Outbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutboxRepository outboxRepository;

    public List<Outbox> findAll() {
        return outboxRepository.findAll();
    }

    public void deleteByEntity(Outbox outbox) {
        outboxRepository.delete(outbox);
    }

    public void save(Outbox outbox) {
        outboxRepository.save(outbox);
    }
}
