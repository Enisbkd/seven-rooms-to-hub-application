package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.service.*;
import com.sbm.sevenroomstohub.service.dto.*;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

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

        Set<ResPosTicketDTO> resPosTickets = reservationPayload.getResPosTickets();
        Set<ResPosticketsItemDTO> resPosTicketItems = reservationPayload.getResPosticketsItems();
        //TODO Identify a solution in case there are many posTickets
        for (ResPosTicketDTO resPosTicket : resPosTickets) {
            resPosTicket.setReservation(savedReservation);
            ResPosTicketDTO savedPosticket = resPosTicketService.save(resPosTicket);
            for (ResPosticketsItemDTO resPosTicketItem : resPosTicketItems) {
                resPosTicketItem.setResPosTicket(savedPosticket);
                resPosticketsItemService.save(resPosTicketItem);
            }
        }

        return reservationDTO;
    }
}
