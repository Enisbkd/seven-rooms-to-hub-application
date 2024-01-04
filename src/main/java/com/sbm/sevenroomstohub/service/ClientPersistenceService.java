package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.domain.ClientTag;
import com.sbm.sevenroomstohub.repository.ClientRepository;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import com.sbm.sevenroomstohub.service.dto.ClientPhotoDTO;
import com.sbm.sevenroomstohub.service.dto.ClientTagDTO;
import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import com.sbm.sevenroomstohub.web.rest.UserResource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientPersistenceService {

    private final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientPhotoService clientPhotoService;

    @Autowired
    ClientVenueStatsService clientVenueStatsService;

    @Autowired
    ClientTagService clientTagService;

    public ClientDTO saveClient(ClientPayload clientPayload) {
        ClientDTO clientDTO = clientPayload.getClient();
        if (clientDTO.getClientPhoto() != null) {
            logger.info("Saving Client Photo having id : " + clientDTO.getClientPhoto().getClientId());
            ClientPhotoDTO clientPhotoDTO = clientPhotoService.save(clientDTO.getClientPhoto());
            clientDTO.setClientPhoto(clientPhotoDTO);
        }
        if (clientDTO.getClientVenueStats() != null) {
            logger.info("Saving ClientVenueStats having id : " + clientDTO.getClientVenueStats().getVenueId());
            ClientVenueStatsDTO clientVenueStatsDTO = clientVenueStatsService.save(clientDTO.getClientVenueStats());
            clientDTO.setClientVenueStats(clientVenueStatsDTO);
        }

        Set<ClientTagDTO> clientTags = clientPayload.getClientTags();

        //        for (ClientTagDTO clientTagDTO : clientTags) {
        //            System.out.println(clientTagDTO);
        //            System.out.println(clientTagDTO.getClass());
        //            clientTagDTO.setClient(clientDTO);
        //            clientTagService.save(clientTagDTO);
        //        }

        return clientDTO;
    }

    public ClientDTO updateClient(ClientPayload clientPayload) {
        return null;
    }
}
