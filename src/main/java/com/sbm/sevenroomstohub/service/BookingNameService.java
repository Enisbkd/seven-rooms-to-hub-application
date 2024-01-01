package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.BookingNameDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.BookingName}.
 */
public interface BookingNameService {
    /**
     * Save a bookingName.
     *
     * @param bookingNameDTO the entity to save.
     * @return the persisted entity.
     */
    BookingNameDTO save(BookingNameDTO bookingNameDTO);

    /**
     * Updates a bookingName.
     *
     * @param bookingNameDTO the entity to update.
     * @return the persisted entity.
     */
    BookingNameDTO update(BookingNameDTO bookingNameDTO);

    /**
     * Partially updates a bookingName.
     *
     * @param bookingNameDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BookingNameDTO> partialUpdate(BookingNameDTO bookingNameDTO);

    /**
     * Get all the bookingNames.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BookingNameDTO> findAll(Pageable pageable);

    /**
     * Get the "id" bookingName.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BookingNameDTO> findOne(Long id);

    /**
     * Delete the "id" bookingName.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
