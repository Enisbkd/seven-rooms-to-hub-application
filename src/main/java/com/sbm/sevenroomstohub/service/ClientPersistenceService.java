package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import org.springframework.stereotype.Service;

public interface ClientPersistenceService {
    ClientDTO saveClient(ClientPayload clientPayload);

    ClientDTO updateClient(ClientPayload clientPayload);

    void deleteClient(ClientPayload clientPayload);
}
