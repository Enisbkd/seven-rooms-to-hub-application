package com.sbm.sevenroomstohub.service.impl;

import static com.sbm.sevenroomstohub.service.dto.ResPosTicketDTO.buildResposticketDto;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.domain.ResPosTicketPayload;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.service.*;
import com.sbm.sevenroomstohub.service.dto.*;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationPersistenceServiceImpl implements ReservationPersistenceService {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ResCustomFieldService resCustomFieldService;

    @Autowired
    ResTagService resTagService;

    @Autowired
    ResTableService resTableService;

    @Autowired
    ResPosTicketService resPosTicketService;

    @Autowired
    ResPosticketsItemService resPosticketsItemService;

    @Override
    public ReservationDTO saveReservation(ReservationPayload reservationPayload) {
        ReservationDTO reservationDTO = reservationPayload.getReservation();
        ReservationDTO savedReservation = reservationService.save(reservationDTO);

        Set<ResCustomFieldDTO> customFields = reservationPayload.getResCustomFields();

        for (ResCustomFieldDTO customFieldDTO : customFields) {
            customFieldDTO.setReservation(savedReservation);
            resCustomFieldService.save(customFieldDTO);
        }

        Set<ResTagDTO> resTagServices = reservationPayload.getResTags();

        for (ResTagDTO resTagDTO : resTagServices) {
            resTagDTO.setReservation(savedReservation);
            resTagService.save(resTagDTO);
        }

        Set<ResTableDTO> resTables = reservationPayload.getResTables();

        for (ResTableDTO resTable : resTables) {
            resTable.setReservation(savedReservation);
            resTableService.save(resTable);
        }

        Set<ResPosTicketPayload> resPosTickets = reservationPayload.getResPosTickets();
        if (resPosTickets != null) {
            for (ResPosTicketPayload resPosTicket : resPosTickets) {
                resPosTicket.setReservation(savedReservation);
                ResPosTicketDTO resPosTicketDTO = buildResposticketDto(resPosTicket);
                ResPosTicketDTO savedResPosTicket = resPosTicketService.save(resPosTicketDTO);
                for (ResPosticketsItemDTO resPosticketsItemDTO : resPosTicket.getResPosticketsItems()) {
                    resPosticketsItemDTO.setResPosTicket(savedResPosTicket);
                    resPosticketsItemService.save(resPosticketsItemDTO);
                }
            }
        }
        return reservationDTO;
    }

    @Override
    public void deleteReservation(ReservationPayload reservationPayload) {
        String resvId = reservationPayload.getReservation().getResvId();
        Long id = reservationService.findByResvId(resvId).get().getId();
        if (id != null) {
            reservationService.delete(id);
        }
    }
}
