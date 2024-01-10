package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import com.sbm.sevenroomstohub.service.dto.ClientTagDTO;
import com.sbm.sevenroomstohub.service.dto.CustomFieldDTO;

public interface ClientPersistenceService {
    ClientDTO upsertClient(ClientPayload clientPayload);

    ClientDTO saveClient(ClientPayload clientPayload);

    ClientDTO updateClient(ClientPayload clientPayload, ClientDTO clientFromDB);

    void deleteClient(ClientPayload clientPayload);

    void updateClientTags(ClientPayload clientPayload, ClientDTO clientFromDB);

    void updateCustomFields(ClientPayload clientPayload, ClientDTO clientFromDB);

    void updateMemberGroups(ClientPayload clientPayload, ClientDTO clientFromDB);
}
