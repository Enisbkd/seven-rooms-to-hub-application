package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import org.springframework.stereotype.Service;

public interface ReservationPersistenceService {
    void upsertReservation(ReservationPayload reservationPayload);

    void deleteReservation(ReservationPayload reservationPayload);
}
