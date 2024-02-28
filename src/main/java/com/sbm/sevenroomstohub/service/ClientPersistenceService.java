package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;

public interface ClientPersistenceService {
    void upsertClient(ClientPayload clientPayload);

    void deleteClient(ClientPayload clientPayload);
}
