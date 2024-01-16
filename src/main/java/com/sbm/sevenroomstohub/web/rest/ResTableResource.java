package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.ResTableRepository;
import com.sbm.sevenroomstohub.service.ResTableService;
import com.sbm.sevenroomstohub.service.dto.ResTableDTO;
import com.sbm.sevenroomstohub.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ResTable}.
 */
@RestController
@RequestMapping("/api/res-tables")
public class ResTableResource {

    private final Logger log = LoggerFactory.getLogger(ResTableResource.class);

    private static final String ENTITY_NAME = "resTable";

    @Value("${springApp.clientApp.name}")
    private String applicationName;

    private final ResTableService resTableService;

    private final ResTableRepository resTableRepository;

    public ResTableResource(ResTableService resTableService, ResTableRepository resTableRepository) {
        this.resTableService = resTableService;
        this.resTableRepository = resTableRepository;
    }

    /**
     * {@code POST  /res-tables} : Create a new resTable.
     *
     * @param resTableDTO the resTableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resTableDTO, or with status {@code 400 (Bad Request)} if the resTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResTableDTO> createResTable(@RequestBody ResTableDTO resTableDTO) throws URISyntaxException {
        log.debug("REST request to save ResTable : {}", resTableDTO);
        if (resTableDTO.getId() != null) {
            throw new BadRequestAlertException("A new resTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResTableDTO result = resTableService.save(resTableDTO);
        return ResponseEntity
            .created(new URI("/api/res-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /res-tables/:id} : Updates an existing resTable.
     *
     * @param id the id of the resTableDTO to save.
     * @param resTableDTO the resTableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resTableDTO,
     * or with status {@code 400 (Bad Request)} if the resTableDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resTableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResTableDTO> updateResTable(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResTableDTO resTableDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ResTable : {}, {}", id, resTableDTO);
        if (resTableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resTableDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResTableDTO result = resTableService.update(resTableDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resTableDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /res-tables/:id} : Partial updates given fields of an existing resTable, field will ignore if it is null
     *
     * @param id the id of the resTableDTO to save.
     * @param resTableDTO the resTableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resTableDTO,
     * or with status {@code 400 (Bad Request)} if the resTableDTO is not valid,
     * or with status {@code 404 (Not Found)} if the resTableDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the resTableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResTableDTO> partialUpdateResTable(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResTableDTO resTableDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResTable partially : {}, {}", id, resTableDTO);
        if (resTableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resTableDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResTableDTO> result = resTableService.partialUpdate(resTableDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resTableDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /res-tables} : get all the resTables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resTables in body.
     */
    @GetMapping("")
    public List<ResTableDTO> getAllResTables() {
        log.debug("REST request to get all ResTables");
        return resTableService.findAll();
    }

    /**
     * {@code GET  /res-tables/:id} : get the "id" resTable.
     *
     * @param id the id of the resTableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resTableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResTableDTO> getResTable(@PathVariable("id") Long id) {
        log.debug("REST request to get ResTable : {}", id);
        Optional<ResTableDTO> resTableDTO = resTableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resTableDTO);
    }

    /**
     * {@code DELETE  /res-tables/:id} : delete the "id" resTable.
     *
     * @param id the id of the resTableDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResTable(@PathVariable("id") Long id) {
        log.debug("REST request to delete ResTable : {}", id);
        resTableService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
