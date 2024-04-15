package io.sharing.server.core.reservation.application.service;

import io.sharing.server.core.reservation.application.port.inp.CreateReservation;
import io.sharing.server.core.reservation.application.port.inp.CreateReservationCommand;
import io.sharing.server.core.reservation.application.port.outp.ReservationRepository;
import io.sharing.server.core.reservation.domain.Reservation;
import io.sharing.server.core.support.exception.TisError;
import io.sharing.server.core.support.exception.TisException;
import io.sharing.server.core.support.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReservationService implements CreateReservation {

    private final ReservationRepository reservationRepository;

    @Override
    public void create(CreateReservationCommand command) {
        log.debug(command.toString());

        if (isDuplicates(command.product().productId())) {
            throw new TisException(TisError.DUPLICATE_RESERVATION);
        }

        Reservation reservation =
                Reservation.createReservation(command.guestId(),
                        command.product().hostId(),
                        command.product().productId());

        reservationRepository.save(reservation);
    }


    private boolean isDuplicates(Long productId) {
        Optional<Reservation> optional = reservationRepository.findPendingReservationByProductId(String.valueOf(productId));

        return !optional.isEmpty();
    }
}
