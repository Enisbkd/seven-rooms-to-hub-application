package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import com.sbm.sevenroomstohub.service.dto.ClientTagDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.ClientTag}.
 */
public interface ClientTagService {
    /**
     * Save a clientTag.
     *
     * @param clientTagDTO the entity to save.
     * @return the persisted entity.
     */
    ClientTagDTO save(ClientTagDTO clientTagDTO);

    /**
     * Updates a clientTag.
     *
     * @param clientTagDTO the entity to update.
     * @return the persisted entity.
     */
    ClientTagDTO update(ClientTagDTO clientTagDTO);

    /**
     * Partially updates a clientTag.
     *
     * @param clientTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClientTagDTO> partialUpdate(ClientTagDTO clientTagDTO);

    /**
     * Get all the clientTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClientTagDTO> findAll(Pageable pageable);

    /**
     * Get the "id" clientTag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientTagDTO> findOne(Long id);

    void deleteTagsByClientId(Long clientId);

    /**
     * Delete the "id" clientTag.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
