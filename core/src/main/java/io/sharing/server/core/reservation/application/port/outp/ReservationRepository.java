package io.sharing.server.core.reservation.application.port.outp;

import io.sharing.server.core.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.productId = :productId AND r.status = 'PENDING'")
    Optional<Reservation> findPendingReservationByProdId(@Param("productId") String productId);
}
