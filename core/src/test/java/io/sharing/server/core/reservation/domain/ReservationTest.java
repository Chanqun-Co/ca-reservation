package io.sharing.server.core.reservation.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReservationTest {

    private Reservation reservation;

    @BeforeEach
    public void init() {
        reservation = Reservation.createReservation("TestGuest", "TestHost", 1L);
    }

    @Test
    @DisplayName("상태변경_REJECTED_성공")
    public void canChangeTo_REJECTED_Success() {
        reservation.reject();

        assertThat(reservation.status).isEqualTo(ReservationStatus.REJECTED);
    }

//    @Test
//    @DisplayName("상태변경_REJECTED_실패")
//    public void canChangeTo_REJECTED_Fail() {
//        //given loop 돌릴 방법 찾기
//        reservation.status = ReservationStatus.REQUEST_CANCEL;
//        reservation.status = ReservationStatus.APPROVED;
//        reservation.status = ReservationStatus.REJECTED;
//
//        // when
//        reservation.reject();
//
//        // then - IllegalStateException
//
//    }

    @Test
    @DisplayName("상태변경_APPROVED_성공")
    public void canChangeTo_APPROVED_Success() {
        reservation.approve();

        assertThat(reservation.status).isEqualTo(ReservationStatus.APPROVED);
    }

//    @Test
//    @DisplayName("상태변경_APPROVED_실패")
//    public void canChangeTo_APPROVED_Fail() {
//
//    }
//
//    @Test
//    @DisplayName("상태변경_REQUEST_CANCEL_성공")
//    public void canChangeTo_REQUEST_CANCEL_Success() {
//
//    }
//
//    @Test
//    @DisplayName("상태변경_REQUEST_CANCEL_실패")
//    public void canChangeTo_REQUEST_CANCEL_Fail() {
//
//    }
//
//    @Test
//    @DisplayName("상태변경_CANCELED_성공")
//    public void canChangeTo_CANCELED_Success() {
//
//    }
//
//    @Test
//    @DisplayName("상태변경_CANCELED_실패")
//    public void canChangeTo_CANCELED_Fail() {
//
//    }
}
