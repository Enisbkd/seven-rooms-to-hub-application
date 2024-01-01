package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.BookingNameRepository;
import com.sbm.sevenroomstohub.service.BookingNameService;
import com.sbm.sevenroomstohub.service.dto.BookingNameDTO;
import com.sbm.sevenroomstohub.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.BookingName}.
 */
@RestController
@RequestMapping("/api/booking-names")
public class BookingNameResource {

    private final Logger log = LoggerFactory.getLogger(BookingNameResource.class);

    private static final String ENTITY_NAME = "bookingName";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookingNameService bookingNameService;

    private final BookingNameRepository bookingNameRepository;

    public BookingNameResource(BookingNameService bookingNameService, BookingNameRepository bookingNameRepository) {
        this.bookingNameService = bookingNameService;
        this.bookingNameRepository = bookingNameRepository;
    }

    /**
     * {@code POST  /booking-names} : Create a new bookingName.
     *
     * @param bookingNameDTO the bookingNameDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookingNameDTO, or with status {@code 400 (Bad Request)} if the bookingName has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BookingNameDTO> createBookingName(@RequestBody BookingNameDTO bookingNameDTO) throws URISyntaxException {
        log.debug("REST request to save BookingName : {}", bookingNameDTO);
        if (bookingNameDTO.getId() != null) {
            throw new BadRequestAlertException("A new bookingName cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookingNameDTO result = bookingNameService.save(bookingNameDTO);
        return ResponseEntity
            .created(new URI("/api/booking-names/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /booking-names/:id} : Updates an existing bookingName.
     *
     * @param id the id of the bookingNameDTO to save.
     * @param bookingNameDTO the bookingNameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookingNameDTO,
     * or with status {@code 400 (Bad Request)} if the bookingNameDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bookingNameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookingNameDTO> updateBookingName(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BookingNameDTO bookingNameDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BookingName : {}, {}", id, bookingNameDTO);
        if (bookingNameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bookingNameDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookingNameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BookingNameDTO result = bookingNameService.update(bookingNameDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bookingNameDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /booking-names/:id} : Partial updates given fields of an existing bookingName, field will ignore if it is null
     *
     * @param id the id of the bookingNameDTO to save.
     * @param bookingNameDTO the bookingNameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookingNameDTO,
     * or with status {@code 400 (Bad Request)} if the bookingNameDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bookingNameDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bookingNameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BookingNameDTO> partialUpdateBookingName(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BookingNameDTO bookingNameDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BookingName partially : {}, {}", id, bookingNameDTO);
        if (bookingNameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bookingNameDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookingNameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BookingNameDTO> result = bookingNameService.partialUpdate(bookingNameDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bookingNameDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /booking-names} : get all the bookingNames.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bookingNames in body.
     */
    @GetMapping("")
    public ResponseEntity<List<BookingNameDTO>> getAllBookingNames(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of BookingNames");
        Page<BookingNameDTO> page = bookingNameService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /booking-names/:id} : get the "id" bookingName.
     *
     * @param id the id of the bookingNameDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookingNameDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookingNameDTO> getBookingName(@PathVariable("id") Long id) {
        log.debug("REST request to get BookingName : {}", id);
        Optional<BookingNameDTO> bookingNameDTO = bookingNameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookingNameDTO);
    }

    /**
     * {@code DELETE  /booking-names/:id} : delete the "id" bookingName.
     *
     * @param id the id of the bookingNameDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingName(@PathVariable("id") Long id) {
        log.debug("REST request to delete BookingName : {}", id);
        bookingNameService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
