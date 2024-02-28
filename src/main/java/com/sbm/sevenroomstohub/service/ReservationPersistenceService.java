package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ReservationPayload;

public interface ReservationPersistenceService {
    void upsertReservation(ReservationPayload reservationPayload);

    void deleteReservation(ReservationPayload reservationPayload);
}
