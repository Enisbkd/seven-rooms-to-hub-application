package com.sbm.sevenroomstohub.repository;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.Reservation;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Reservation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByResvId(String resvId);
}
