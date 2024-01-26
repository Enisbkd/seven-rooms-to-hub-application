package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import com.sbm.sevenroomstohub.service.dto.ClientTagDTO;
import com.sbm.sevenroomstohub.service.dto.CustomFieldDTO;

public interface ClientPersistenceService {
    void upsertClient(ClientPayload clientPayload);

    void deleteClient(ClientPayload clientPayload);
}
