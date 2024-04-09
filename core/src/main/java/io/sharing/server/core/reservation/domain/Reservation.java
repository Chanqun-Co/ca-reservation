package io.sharing.server.core.reservation.domain;

import io.sharing.server.core.support.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.OffsetDateTime;

import static io.sharing.server.core.reservation.domain.ReservationStatus.*;


/**
 * 예약
 */
@Entity
public class Reservation extends BaseEntity {

    /** 호스트 UUID를 통한 정보 */
    String hostId;

    /** 게스트 UUID를 통한 정보*/
    String guestId;

    Long productId;

    /** 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    ReservationStatus status;

    /** 상태변경일시 */
    OffsetDateTime updateAt;

    /** 생성일시 */
    OffsetDateTime createdAt;

    public void approve() {
        check (status.canChangeTo(APPROVED));

        this.updateAt = OffsetDateTime.now();
        this.status = APPROVED;
    }

    public void reject() {
        check (status.canChangeTo(REJECTED));

        this.updateAt = OffsetDateTime.now();
        this.status = REJECTED;
    }

    public void requestCancel() {
        check(status.canChangeTo(REQUEST_CANCEL));

        this.updateAt = OffsetDateTime.now();
        this.status = REQUEST_CANCEL;
    }

    public void cancel() {
        check(status.canChangeTo(CANCELED));

        this.updateAt = OffsetDateTime.now();
        this.status = CANCELED;
    }

    private void check(boolean value) {
        if (value) {
            throw new IllegalStateException();
        }
    }

    public static Reservation createReservation(String guest, String host, Long productId) {
        Reservation reservation = new Reservation();
        reservation.guestId = guest;
        reservation.hostId = host;
        reservation.productId = productId;
        reservation.status = PENDING;
        reservation.createdAt = OffsetDateTime.now();

        return reservation;
    }
}
