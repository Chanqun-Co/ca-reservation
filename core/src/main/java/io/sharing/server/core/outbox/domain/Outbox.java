package io.sharing.server.core.outbox.domain;

import io.sharing.server.core.support.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Outbox extends BaseEntity {

    private String topicName;
    private String message;
}
