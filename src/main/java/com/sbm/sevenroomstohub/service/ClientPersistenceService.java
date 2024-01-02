package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.repository.ClientRepository;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import com.sbm.sevenroomstohub.service.dto.ClientPhotoDTO;
import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPersistenceService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientPhotoService clientPhotoService;

    @Autowired
    ClientVenueStatsService clientVenueStatsService;

    public ClientDTO saveClient(ClientPayload clientPayload) {
        ClientPhotoDTO clientPhotoDTO = clientPhotoService.save(clientPayload.getClientDTO().getClientPhoto());
        ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsService.save(clientPayload.getClientDTO().getClientVenueStats());
        clientPayload.getClientDTO().setClientPhoto(clientPhotoDTO);
        clientPayload.getClientDTO().setClientVenueStats(clientVenueStatsDTO);
        clientService.save(clientPayload.getClientDTO());
        return clientPayload.getClientDTO();
    }

    public ClientDTO updateClient(ClientPayload clientPayload) {
        return null;
    }
}
