package io.sharing.server.api.reservation.adapter.inp.web;

import io.sharing.server.core.reservation.application.port.inp.CreateReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationApi {

    private final CreateReservation createReservation;

    @PostMapping("/create")
    public void create(@RequestBody ReservationReq req) {
        createReservation.create(req.toCommand());
    }
}
