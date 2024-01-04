package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;

public interface ReservationPersistenceService {
    ReservationDTO saveReservation(ReservationPayload reservationPayload);
}
