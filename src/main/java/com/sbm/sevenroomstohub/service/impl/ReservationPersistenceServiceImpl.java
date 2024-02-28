package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.service.ClientService;
import com.sbm.sevenroomstohub.service.ReservationPersistenceService;
import com.sbm.sevenroomstohub.service.ReservationService;
import com.sbm.sevenroomstohub.utils.TimestampUtils;
import java.time.LocalDateTime;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationPersistenceServiceImpl implements ReservationPersistenceService {

    private final Logger logger = LoggerFactory.getLogger(ReservationPersistenceServiceImpl.class);

    @Autowired
    ReservationService reservationService;

    @Autowired
    ClientService clientService;

    public ReservationPayload upsertReservation(ReservationPayload reservationPayload) {
        Reservation payloadRes = reservationPayload.getReservation();
        String resvId = payloadRes.getResvId();
        Optional<Reservation> resvFromDB = reservationService.findByResvId(resvId);

        if (resvFromDB.isPresent()) {
            String updateDateInDB = resvFromDB.get().getUpdated();
            String updateDateInPayload = reservationPayload.getReservation().getUpdated();

            LocalDateTime timestampInDB = TimestampUtils.convertStringToTimestamp(updateDateInDB);
            LocalDateTime timestampInPayload = TimestampUtils.convertStringToTimestamp(updateDateInPayload);

            logger.debug("updateDate in DB : " + timestampInDB);
            logger.debug("updateDate in Payload : " + timestampInPayload);

            if (timestampInPayload.isAfter(timestampInDB)) {
                logger.debug("Payload record is newer, updating Entity having id : " + resvFromDB.get().getId());
                reservationPayload.getReservation().setId(resvFromDB.get().getId());
                reservationService.save(reservationPayload);
                return reservationPayload;
            } else {
                logger.debug("Payload record is older, Aborting update ...");
                return null;
            }
        } else {
            logger.debug("Reservation with externalID " + resvId + " does not exist in DB , Inserting ...");
            reservationService.save(reservationPayload);
            return reservationPayload;
        }
    }

    @Override
    public void deleteReservation(ReservationPayload reservationPayload) {
        String resvId = reservationPayload.getReservation().getResvId();
        if (reservationService.findByResvId(resvId).isPresent()) {
            Long id = reservationService.findByResvId(resvId).get().getId();
            if (id != null) {
                logger.debug("Deleting Reservation with id = " + id);
                reservationService.delete(id);
            }
        }
    }
}
