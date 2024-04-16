package io.sharing.server.core.reservation.application.service;

import io.sharing.server.core.reservation.application.port.outp.ReservationRepository;
import io.sharing.server.core.reservation.domain.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void save() {
        Reservation reservation = Reservation.createReservation("UUID345678", "UUID123456", 1L);
        reservationRepository.save(reservation);
    }


    @Test
    @DisplayName("동일 상품으로 예약된 정보가 존재하지 않을 경우 예약 가능")
    public void checkForDuplicates_success() {
        String productId = "2";

        Optional<Reservation> optional = reservationRepository.findPendingReservationByProductId(productId);

        assertThat(optional.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("동일 상품으로 예약된 정보가 존재할 경우 예약 불가")
    public void checkForDuplicates_fail() {
        String productId = "1";

        Optional<Reservation> optional = reservationRepository.findPendingReservationByProductId(productId);

        assertThat(optional.isEmpty()).isFalse();
    }
}