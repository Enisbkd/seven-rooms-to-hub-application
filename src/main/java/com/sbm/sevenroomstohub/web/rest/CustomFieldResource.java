package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.CustomFieldRepository;
import com.sbm.sevenroomstohub.service.CustomFieldService;
import com.sbm.sevenroomstohub.service.dto.CustomFieldDTO;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.CustomField}.
 */
@RestController
@RequestMapping("/api/custom-fields")
public class CustomFieldResource {

    private final Logger log = LoggerFactory.getLogger(CustomFieldResource.class);

    private static final String ENTITY_NAME = "customField";

    @Value("${common.clientApp.name}")
    private String applicationName;

    private final CustomFieldService customFieldService;

    private final CustomFieldRepository customFieldRepository;

    public CustomFieldResource(CustomFieldService customFieldService, CustomFieldRepository customFieldRepository) {
        this.customFieldService = customFieldService;
        this.customFieldRepository = customFieldRepository;
    }

    /**
     * {@code POST  /custom-fields} : Create a new customField.
     *
     * @param customFieldDTO the customFieldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customFieldDTO, or with status {@code 400 (Bad Request)} if the customField has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CustomFieldDTO> createCustomField(@RequestBody CustomFieldDTO customFieldDTO) throws URISyntaxException {
        log.debug("REST request to save CustomField : {}", customFieldDTO);
        if (customFieldDTO.getId() != null) {
            throw new BadRequestAlertException("A new customField cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomFieldDTO result = customFieldService.save(customFieldDTO);
        return ResponseEntity
            .created(new URI("/api/custom-fields/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /custom-fields/:id} : Updates an existing customField.
     *
     * @param id the id of the customFieldDTO to save.
     * @param customFieldDTO the customFieldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customFieldDTO,
     * or with status {@code 400 (Bad Request)} if the customFieldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customFieldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomFieldDTO> updateCustomField(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomFieldDTO customFieldDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CustomField : {}, {}", id, customFieldDTO);
        if (customFieldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customFieldDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CustomFieldDTO result = customFieldService.update(customFieldDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customFieldDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /custom-fields/:id} : Partial updates given fields of an existing customField, field will ignore if it is null
     *
     * @param id the id of the customFieldDTO to save.
     * @param customFieldDTO the customFieldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customFieldDTO,
     * or with status {@code 400 (Bad Request)} if the customFieldDTO is not valid,
     * or with status {@code 404 (Not Found)} if the customFieldDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the customFieldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CustomFieldDTO> partialUpdateCustomField(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomFieldDTO customFieldDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomField partially : {}, {}", id, customFieldDTO);
        if (customFieldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customFieldDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomFieldDTO> result = customFieldService.partialUpdate(customFieldDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customFieldDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /custom-fields} : get all the customFields.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customFields in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CustomFieldDTO>> getAllCustomFields(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of CustomFields");
        Page<CustomFieldDTO> page = customFieldService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /custom-fields/:id} : get the "id" customField.
     *
     * @param id the id of the customFieldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customFieldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomFieldDTO> getCustomField(@PathVariable("id") Long id) {
        log.debug("REST request to get CustomField : {}", id);
        Optional<CustomFieldDTO> customFieldDTO = customFieldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customFieldDTO);
    }

    /**
     * {@code DELETE  /custom-fields/:id} : delete the "id" customField.
     *
     * @param id the id of the customFieldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomField(@PathVariable("id") Long id) {
        log.debug("REST request to delete CustomField : {}", id);
        customFieldService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
