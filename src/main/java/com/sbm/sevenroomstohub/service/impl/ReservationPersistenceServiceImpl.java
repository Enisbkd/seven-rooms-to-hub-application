package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.repository.ReservationRepository;
import com.sbm.sevenroomstohub.service.ReservationPersistenceService;
import com.sbm.sevenroomstohub.service.ReservationService;
import com.sbm.sevenroomstohub.utils.TimestampUtils;
import java.sql.Timestamp;
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
    ReservationRepository reservationRepository;

    public void upsertReservation(ReservationPayload reservationPayload) {
        Reservation payloadRes = reservationPayload.getReservation();
        String resvId = payloadRes.getResvId();
        Optional<Reservation> resvFromDB = reservationService.findByResvId(resvId);
        if (resvFromDB.isPresent()) {
            String updateDateInDB = resvFromDB.get().getUpdated();
            String updateDateInPayload = reservationPayload.getReservation().getUpdated();

            Timestamp timestampInDB = TimestampUtils.convertStringToTimestamp(updateDateInDB);
            Timestamp timestampInPayload = TimestampUtils.convertStringToTimestamp(updateDateInPayload);

            logger.debug("updateDate in DB : " + timestampInDB);
            logger.debug("updateDate in Payload : " + timestampInPayload);

            if (timestampInPayload != null) {
                if (timestampInPayload.after(timestampInDB)) {
                    logger.debug("Payload record is newer, updating Entity ...");
                    reservationPayload.getReservation().setId(resvFromDB.get().getId());
                    reservationRepository.delete(resvFromDB.get());
                    reservationService.save(reservationPayload);
                }
            }
        } else {
            logger.debug("Reservation with externalID " + resvId + "does not exist in DB , Inserting ...");
            reservationService.save(reservationPayload);
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
