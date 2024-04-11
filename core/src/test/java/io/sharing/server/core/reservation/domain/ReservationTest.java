package io.sharing.server.core.reservation.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

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

    @ParameterizedTest
    @DisplayName("상태변경_REJECTED_실패")
    @EnumSource(value = ReservationStatus.class, names = {"REQUEST_CANCEL", "APPROVED", "REJECTED"})
    public void canChangeTo_REJECTED_Fail(ReservationStatus value) {
        reservation.status = value;

        assertThatThrownBy(() -> reservation.reject()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("상태변경_APPROVED_성공")
    public void canChangeTo_APPROVED_Success() {
        reservation.approve();

        assertThat(reservation.status).isEqualTo(ReservationStatus.APPROVED);
    }

    @ParameterizedTest
    @DisplayName("상태변경_APPROVED_실패")
    @EnumSource(value = ReservationStatus.class, names = {"REQUEST_CANCEL", "APPROVED", "REJECTED", "CANCELED"})
    public void canChangeTo_APPROVED_Fail(ReservationStatus value) {
        reservation.status = value;

        assertThatThrownBy(() -> reservation.approve()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("상태변경_REQUEST_CANCEL_성공")
    public void canChangeTo_REQUEST_CANCEL_Success() {
        reservation.status = ReservationStatus.APPROVED;

        reservation.requestCancel();

        assertThat(reservation.status).isEqualTo(ReservationStatus.REQUEST_CANCEL);
    }

    @ParameterizedTest
    @DisplayName("상태변경_REQUEST_CANCEL_실패")
    @EnumSource(value = ReservationStatus.class, names = {"REQUEST_CANCEL", "PENDING", "REJECTED", "CANCELED"})
    public void canChangeTo_REQUEST_CANCEL_Fail(ReservationStatus value) {
        reservation.status = value;

        assertThatThrownBy(() -> reservation.requestCancel()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("상태변경_CANCELED_성공")
    public void canChangeTo_CANCELED_Success() {
        reservation.status = ReservationStatus.REQUEST_CANCEL;

        reservation.cancel();

        assertThat(reservation.status).isEqualTo(ReservationStatus.CANCELED);
    }

    @ParameterizedTest
    @DisplayName("상태변경_CANCELED_실패")
    @EnumSource(value = ReservationStatus.class, names = {"PENDING", "APPROVED", "REJECTED", "CANCELED"})
    public void canChangeTo_CANCELED_Fail(ReservationStatus value) {
        reservation.status = value;

        assertThatThrownBy(() -> reservation.cancel()).isInstanceOf(IllegalStateException.class);
    }
}
