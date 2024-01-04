package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.dto.*;
import com.sbm.sevenroomstohub.web.rest.UserResource;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPersistenceService {

    private final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    CustomFieldService customFieldService;

    @Autowired
    ClientService clientService;

    @Autowired
    MemberGroupService memberGroupService;

    @Autowired
    ClientPhotoService clientPhotoService;

    @Autowired
    ClientVenueStatsService clientVenueStatsService;

    @Autowired
    ClientTagService clientTagService;

    @Autowired
    BookingNameService bookingNameService;

    public ClientDTO saveClient(ClientPayload clientPayload) {
        ClientDTO clientDTO = clientPayload.getClient();
        if (clientDTO.getClientPhoto() != null) {
            logger.info("Saving Client Photo having id : " + clientDTO.getClientPhoto().getId());
            ClientPhotoDTO clientPhotoDTO = clientPhotoService.save(clientDTO.getClientPhoto());
            clientDTO.setClientPhoto(clientPhotoDTO);
        }
        if (clientDTO.getClientVenueStats() != null) {
            logger.info("Saving ClientVenueStats having id : " + clientDTO.getClientVenueStats().getVenueId());
            ClientVenueStatsDTO clientVenueStatsSaved = clientVenueStatsService.save(clientDTO.getClientVenueStats());

            Set<BookingNameDTO> bookingNames = clientPayload.getBookingNames();

            for (BookingNameDTO bookingNameDTO : bookingNames) {
                bookingNameDTO.setClientVenueStats(clientVenueStatsSaved);
                bookingNameService.save(bookingNameDTO);
            }

            clientDTO.setClientVenueStats(clientVenueStatsSaved);
        }

        ClientDTO clientsaved = clientService.save(clientDTO);

        Set<ClientTagDTO> clientTags = clientPayload.getClientTags();

        for (ClientTagDTO clientTagDTO : clientTags) {
            clientTagDTO.setClient(clientsaved);
            clientTagService.save(clientTagDTO);
        }

        Set<CustomFieldDTO> customFields = clientPayload.getCustomFields();

        for (CustomFieldDTO customFieldDTO : customFields) {
            customFieldDTO.setClient(clientsaved);
            customFieldService.save(customFieldDTO);
        }

        Set<MemberGroupDTO> memberGroups = clientPayload.getMemberGroups();

        for (MemberGroupDTO memberGroupDTO : memberGroups) {
            memberGroupDTO.setClient(clientsaved);
            memberGroupService.save(memberGroupDTO);
        }

        return clientDTO;
    }

    public ClientDTO updateClient(ClientPayload clientPayload) {
        return null;
    }
}
