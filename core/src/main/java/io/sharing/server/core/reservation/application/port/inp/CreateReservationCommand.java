package io.sharing.server.core.reservation.application.port.inp;

import io.sharing.server.core.product.ProductDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReservationCommand {
    private String guestId;
    private ProductDto product;
}
