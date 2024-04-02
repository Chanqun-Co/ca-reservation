package io.sharing.server.api.reservation.adapter.inp.web;

import io.sharing.server.api.reservation.application.port.inp.ReservationCreate;
import io.sharing.server.core.reservation.application.port.inp.CreateReservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ca-reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationApi {

    private final ReservationCreate reservationCreate;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/create")
    public void create(@RequestBody ReservationReq req) {
        reservationCreate.create(req);
    }
}
