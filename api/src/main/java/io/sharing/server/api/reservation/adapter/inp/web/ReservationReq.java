package io.sharing.server.api.reservation.adapter.inp.web;

import io.sharing.server.core.reservation.application.port.inp.CreateReservationCommand;
import lombok.NonNull;

public class ReservationReq {
    @NonNull
    private String guest;

    @NonNull
    private String host;

    @NonNull
    private String productId;

    public CreateReservationCommand toCommand() {
        return CreateReservationCommand.builder()
                .guest(this.guest)
                .host(this.host)
                .productId(this.productId)
                .build();
    }
}
