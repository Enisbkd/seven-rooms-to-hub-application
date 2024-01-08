package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.*;
import com.sbm.sevenroomstohub.service.dto.*;
import com.sbm.sevenroomstohub.utils.TimestampUtils;
import com.sbm.sevenroomstohub.web.rest.UserResource;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPersistenceServiceImpl implements ClientPersistenceService {

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

    public ClientDTO upsertClient(ClientPayload clientPayload) {
        String clientId = clientPayload.getClient().getClientId();
        Optional<ClientDTO> clientFromDB = clientService.findByClientId(clientId);
        if (clientFromDB.isPresent()) {
            String updateDateInDB = clientFromDB.get().getUpdatedDate();
            String updateDateInPayload = clientPayload.getClient().getUpdatedDate();

            Timestamp timestampInDB = TimestampUtils.convertStringToTimestamp(updateDateInDB);
            Timestamp timestampInPayload = TimestampUtils.convertStringToTimestamp(updateDateInPayload);
            if (timestampInDB != null) {
                if (timestampInDB.before(timestampInPayload)) {
                    updateClient(clientPayload, clientFromDB);
                }
            }
        } else saveClient(clientPayload);
        return null;
    }

    public ClientDTO saveClient(ClientPayload clientPayload) {
        ClientDTO clientDTO = clientPayload.getClient();
        if (clientDTO.getClientPhoto() != null) {
            ClientPhotoDTO savedClientPhoto = clientPhotoService.save(clientDTO.getClientPhoto());
            clientDTO.setClientPhoto(savedClientPhoto);
        }
        if (clientDTO.getClientVenueStats() != null) {
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

    public ClientDTO updateClient(ClientPayload clientPayload, ClientDTO clientFromDB) {
        ClientDTO clientDTO = clientPayload.getClient();
        if (clientDTO.getClientPhoto() != null) {
            ClientPhotoDTO savedClientPhoto = clientPhotoService.save(clientDTO.getClientPhoto());
            clientDTO.setClientPhoto(savedClientPhoto);
        }
        return null;
    }

    @Override
    public void deleteClient(ClientPayload clientPayload) {
        String clientId = clientPayload.getClient().getClientId();
        if (clientService.findByClientId(clientId).isPresent()) {
            Long id = clientService.findByClientId(clientId).get().getId();
            if (clientService.findByClientId(clientId).isPresent()) {
                clientService.delete(id);
            }
        }
    }
}
