package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ReservationPayload;

public interface ReservationPersistenceService {
    ReservationPayload upsertReservation(ReservationPayload reservationPayload);

    void deleteReservation(ReservationPayload reservationPayload);
}
