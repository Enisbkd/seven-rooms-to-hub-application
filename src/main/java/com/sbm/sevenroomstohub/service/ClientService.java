package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.Client}.
 */
public interface ClientService {
    /**
     * Save a client.
     *
     * @param clientDTO the entity to save.
     * @return the persisted entity.
     */
    ClientDTO save(ClientDTO clientDTO);

    Client save(ClientPayload clientPayload);

    /**
     * Updates a client.
     *
     * @param clientDTO the entity to update.
     * @return the persisted entity.
     */
    ClientDTO update(ClientDTO clientDTO);

    /**
     * Partially updates a client.
     *
     * @param clientDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClientDTO> partialUpdate(ClientDTO clientDTO);

    /**
     * Get all the clients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClientDTO> findAll(Pageable pageable);

    /**
     * Get the "id" client.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientDTO> findOne(Long id);

    /**
     * Get the "clientId" client.
     *
     * @param clientId the business id of the entity.
     * @return the entity.
     */

    Optional<Client> findByClientId(String clientId);

    /**
     * Delete the "id" client.
     *
     * @param id the id of the entity.
     */

    void delete(Long id);
}
