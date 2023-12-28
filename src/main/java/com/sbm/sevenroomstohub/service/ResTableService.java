package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.ResTableDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.ResTable}.
 */
public interface ResTableService {
    /**
     * Save a resTable.
     *
     * @param resTableDTO the entity to save.
     * @return the persisted entity.
     */
    ResTableDTO save(ResTableDTO resTableDTO);

    /**
     * Updates a resTable.
     *
     * @param resTableDTO the entity to update.
     * @return the persisted entity.
     */
    ResTableDTO update(ResTableDTO resTableDTO);

    /**
     * Partially updates a resTable.
     *
     * @param resTableDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResTableDTO> partialUpdate(ResTableDTO resTableDTO);

    /**
     * Get all the resTables.
     *
     * @return the list of entities.
     */
    List<ResTableDTO> findAll();

    /**
     * Get the "id" resTable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResTableDTO> findOne(Long id);

    /**
     * Delete the "id" resTable.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
