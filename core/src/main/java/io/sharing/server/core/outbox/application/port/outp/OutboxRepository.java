package io.sharing.server.core.outbox.application.port.outp;

import io.sharing.server.core.outbox.domain.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
}
