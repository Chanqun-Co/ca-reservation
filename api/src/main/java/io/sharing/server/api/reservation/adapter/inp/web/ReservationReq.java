package io.sharing.server.api.reservation.adapter.inp.web;

import io.sharing.server.core.product.Product;
import io.sharing.server.core.reservation.application.port.inp.CreateReservationCommand;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ReservationReq {
    @NonNull
    private String guestId;

    @NonNull
    private Long productId;

    public CreateReservationCommand toCommand(Product product) {
        return CreateReservationCommand.builder()
                .guestId(this.guestId)
                .product(product)
                .build();
    }
}
