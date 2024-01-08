package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import java.util.Optional;

public interface ClientPersistenceService {
    ClientDTO upsertClient(ClientPayload clientPayload);

    ClientDTO saveClient(ClientPayload clientPayload);

    ClientDTO updateClient(ClientPayload clientPayload, Optional<ClientDTO> clientFromDB);

    void deleteClient(ClientPayload clientPayload);
}
