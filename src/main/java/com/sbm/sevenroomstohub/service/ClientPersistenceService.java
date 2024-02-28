package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;

public interface ClientPersistenceService {
    ClientPayload upsertClient(ClientPayload clientPayload);

    void deleteClient(ClientPayload clientPayload);
}
