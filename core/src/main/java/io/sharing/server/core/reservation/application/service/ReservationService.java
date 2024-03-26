package io.sharing.server.core.reservation.application.service;

import io.sharing.server.core.reservation.application.port.inp.CreateReservation;
import io.sharing.server.core.reservation.application.port.inp.CreateReservationCommand;
import io.sharing.server.core.reservation.application.port.outp.ReservationRepository;
import io.sharing.server.core.reservation.domain.Reservation;
import io.sharing.server.core.support.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ReservationService implements CreateReservation {

    private final ReservationRepository reservationRepository;

    private final RestTemplate restTemplate;

    @Override
    public void create(CreateReservationCommand command) {
        // 상품 예약가능 상태 확인
        Object product = getProductInfo(command.getProductId());

        // 결제 서비스 호출
        // 상품 금액 필수
        invokePayment();

        // 저장
        Reservation reservation = Reservation.createReservation(command.getGuest(), command.getHost());
        reservationRepository.save(reservation);
    }

    /**
     * 상품 정보 조회
     * */
    private Object getProductInfo(String prodId) {
        // RestTemplate
        restTemplate.getForObject("", Object.class);
        return null;
    }

    private void invokePayment() {

    }
}
