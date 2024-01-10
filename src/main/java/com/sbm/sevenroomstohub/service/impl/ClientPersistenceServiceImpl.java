package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.*;
import com.sbm.sevenroomstohub.service.dto.*;
import com.sbm.sevenroomstohub.utils.TimestampUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPersistenceServiceImpl implements ClientPersistenceService {

    private final Logger logger = LoggerFactory.getLogger(ClientPersistenceServiceImpl.class);

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

    @PersistenceContext
    EntityManager entityManager;

    public ClientDTO upsertClient(ClientPayload clientPayload) {
        String clientId = clientPayload.getClient().getClientId();
        Optional<ClientDTO> clientFromDB = clientService.findByClientId(clientId);
        if (clientFromDB.isPresent()) {
            String updateDateInDB = clientFromDB.get().getUpdatedDate();
            String updateDateInPayload = clientPayload.getClient().getUpdatedDate();

            Timestamp timestampInDB = TimestampUtils.convertStringToTimestamp(updateDateInDB);
            Timestamp timestampInPayload = TimestampUtils.convertStringToTimestamp(updateDateInPayload);

            logger.debug("updateDate in DB : " + timestampInDB);
            logger.debug("updateDate in Payload : " + timestampInPayload);

            if (timestampInPayload != null) {
                if (timestampInPayload.after(timestampInDB)) {
                    logger.debug("Payload record is newer, updating Entity ...");
                    updateClient(clientPayload, clientFromDB.get());
                    updateClientTags(clientPayload, clientFromDB.get());
                    updateCustomFields(clientPayload, clientFromDB.get());
                    updateMemberGroups(clientPayload, clientFromDB.get());
                }
            }
        } else {
            logger.debug("Client with externalID " + clientId + "does not exist in DB , Inserting ...");
            saveClient(clientPayload);
        }
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

            saveBookingNames(clientPayload, clientVenueStatsSaved);

            clientDTO.setClientVenueStats(clientVenueStatsSaved);
        }

        ClientDTO clientsaved = clientService.save(clientDTO);

        saveClientTags(clientPayload, clientsaved);

        saveCustomFields(clientPayload, clientsaved);

        saveMemberGroups(clientPayload, clientsaved);

        return clientDTO;
    }

    private void saveBookingNames(ClientPayload clientPayload, ClientVenueStatsDTO clientVenueStatsSaved) {
        Set<BookingNameDTO> bookingNames = clientPayload.getBookingNames();

        for (BookingNameDTO bookingNameDTO : bookingNames) {
            bookingNameDTO.setClientVenueStats(clientVenueStatsSaved);
            bookingNameService.save(bookingNameDTO);
        }
    }

    private void saveMemberGroups(ClientPayload clientPayload, ClientDTO clientsaved) {
        Set<MemberGroupDTO> memberGroups = clientPayload.getMemberGroups();

        for (MemberGroupDTO memberGroupDTO : memberGroups) {
            memberGroupDTO.setClient(clientsaved);
            memberGroupService.save(memberGroupDTO);
        }
    }

    private void saveCustomFields(ClientPayload clientPayload, ClientDTO clientsaved) {
        Set<CustomFieldDTO> customFields = clientPayload.getCustomFields();

        for (CustomFieldDTO customFieldDTO : customFields) {
            customFieldDTO.setClient(clientsaved);
            customFieldService.save(customFieldDTO);
        }
    }

    private void saveClientTags(ClientPayload clientPayload, ClientDTO clientsaved) {
        Set<ClientTagDTO> clientTags = clientPayload.getClientTags();

        for (ClientTagDTO clientTagDTO : clientTags) {
            clientTagDTO.setClient(clientsaved);
            clientTagService.save(clientTagDTO);
        }
    }

    @Override
    public ClientDTO updateClient(ClientPayload clientPayload, ClientDTO clientFromDB) {
        ClientDTO newclientDTO = clientPayload.getClient();
        Long id = clientFromDB.getId();
        newclientDTO.setId(id);
        clientService.partialUpdate(newclientDTO);
        return newclientDTO;
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

    @Override
    public void updateClientTags(ClientPayload clientPayload, ClientDTO clientFromDB) {
        Long clientId = clientFromDB.getId();
        clientTagService.deleteTagsByClientId(clientId);
        saveClientTags(clientPayload, clientFromDB);
    }

    public void updateMemberGroups(ClientPayload clientPayload, ClientDTO clientFromDB) {
        Long clientId = clientFromDB.getId();
        customFieldService.deleteCustomFieldsByClientId(clientId);
        saveCustomFields(clientPayload, clientFromDB);
    }

    @Override
    public void updateCustomFields(ClientPayload clientPayload, ClientDTO clientFromDB) {
        Long clientId = clientFromDB.getId();
        customFieldService.deleteCustomFieldsByClientId(clientId);
        saveCustomFields(clientPayload, clientFromDB);
    }
}
