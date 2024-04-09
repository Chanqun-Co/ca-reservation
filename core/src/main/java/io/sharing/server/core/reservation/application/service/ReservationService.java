package io.sharing.server.core.reservation.application.service;

import io.sharing.server.core.reservation.application.port.inp.CreateReservation;
import io.sharing.server.core.reservation.application.port.inp.CreateReservationCommand;
import io.sharing.server.core.reservation.application.port.outp.ReservationRepository;
import io.sharing.server.core.reservation.domain.Reservation;
import io.sharing.server.core.support.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

        // 중복 체크
        if (checkForDuplicates(command.getProduct().getProductId())) {
            throw new IllegalStateException("이미 예약된 상품입니다.");
        }

        // 저장
        Reservation reservation =
                Reservation.createReservation(command.getGuestId(),
                        command.getProduct().getHostId()
                        , command.getProduct().getProductId());

        reservationRepository.save(reservation);
    }


    private boolean checkForDuplicates(Long productId) {
        boolean result = true;
        Optional<Reservation> optional = reservationRepository.findPendingReservationByProdId(String.valueOf(productId));

        // 동일 상품으로 예약된 정보가 없는 경우
        if (optional.isEmpty()) {
            result = false;
        }

        return result;
    }
}
