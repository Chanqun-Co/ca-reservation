package io.sharing.server.core.reservation.application.port.inp;

import io.sharing.server.core.product.Product;
import lombok.*;

@Builder
public record CreateReservationCommand(String guestId, Product product) {
}
