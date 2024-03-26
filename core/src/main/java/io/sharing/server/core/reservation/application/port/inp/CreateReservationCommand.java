package io.sharing.server.core.reservation.application.port.inp;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CreateReservationCommand {
    private String host;
    private String guest;
    private String productId;
}
