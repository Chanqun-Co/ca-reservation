package io.sharing.server.core.reservation.application.port.inp;

import org.springframework.stereotype.Service;

@Service
public interface CreateReservation {
    /**
     * 예약을 등록한다.
     * */
    void create(CreateReservationCommand command);
}

