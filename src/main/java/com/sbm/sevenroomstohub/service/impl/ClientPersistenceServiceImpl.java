package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.ClientPersistenceService;
import com.sbm.sevenroomstohub.service.ClientService;
import com.sbm.sevenroomstohub.utils.TimestampUtils;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPersistenceServiceImpl implements ClientPersistenceService {

    private final Logger logger = LoggerFactory.getLogger(ClientPersistenceServiceImpl.class);

    @Autowired
    ClientService clientService;

    public void upsertClient(ClientPayload clientPayload) {
        String clientId = clientPayload.getClient().getClientId();
        Optional<Client> clientFromDB = clientService.findByClientId(clientId);
        if (clientFromDB.isPresent()) {
            String updateDateInDB = clientFromDB.get().getUpdatedDate();
            String updateDateInPayload = clientPayload.getClient().getUpdatedDate();

            LocalDateTime timestampInDB = TimestampUtils.convertStringToTimestamp(updateDateInDB);
            LocalDateTime timestampInPayload = TimestampUtils.convertStringToTimestamp(updateDateInPayload);

            logger.debug("updateDate in DB : " + timestampInDB);
            logger.debug("updateDate in Payload : " + timestampInPayload);

            if (timestampInPayload.isAfter(timestampInDB)) {
                logger.debug("Payload record is newer, updating Entity having id : " + clientFromDB.get().getId());
                clientPayload.getClient().setId(clientFromDB.get().getId());
                clientService.save(clientPayload);
            } else {
                logger.debug("Payload record is older, Aborting update ...");
            }
        } else {
            logger.debug("Client with externalID " + clientId + " does not exist in DB , Inserting ...");
            clientService.save(clientPayload);
        }
    }

    @Override
    public void deleteClient(ClientPayload clientPayload) {
        String clientId = clientPayload.getClient().getClientId();
        if (clientService.findByClientId(clientId).isPresent()) {
            Long id = clientService.findByClientId(clientId).get().getId();
            if (id != null) {
                logger.debug("Deleting Client with id = " + id);
                clientService.delete(id);
            }
        }
    }
}
