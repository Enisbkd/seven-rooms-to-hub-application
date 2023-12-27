package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.CustomField;
import com.sbm.sevenroomstohub.repository.CustomFieldRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.CustomField}.
 */
@RestController
@RequestMapping("/api/custom-fields")
@Transactional
public class CustomFieldResource {

    private final Logger log = LoggerFactory.getLogger(CustomFieldResource.class);

    private static final String ENTITY_NAME = "customField";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomFieldRepository customFieldRepository;

    public CustomFieldResource(CustomFieldRepository customFieldRepository) {
        this.customFieldRepository = customFieldRepository;
    }

    /**
     * {@code POST  /custom-fields} : Create a new customField.
     *
     * @param customField the customField to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customField, or with status {@code 400 (Bad Request)} if the customField has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CustomField> createCustomField(@RequestBody CustomField customField) throws URISyntaxException {
        log.debug("REST request to save CustomField : {}", customField);
        if (customField.getId() != null) {
            throw new BadRequestAlertException("A new customField cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomField result = customFieldRepository.save(customField);
        return ResponseEntity
            .created(new URI("/api/custom-fields/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /custom-fields/:id} : Updates an existing customField.
     *
     * @param id the id of the customField to save.
     * @param customField the customField to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customField,
     * or with status {@code 400 (Bad Request)} if the customField is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customField couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomField> updateCustomField(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomField customField
    ) throws URISyntaxException {
        log.debug("REST request to update CustomField : {}, {}", id, customField);
        if (customField.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customField.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CustomField result = customFieldRepository.save(customField);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customField.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /custom-fields/:id} : Partial updates given fields of an existing customField, field will ignore if it is null
     *
     * @param id the id of the customField to save.
     * @param customField the customField to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customField,
     * or with status {@code 400 (Bad Request)} if the customField is not valid,
     * or with status {@code 404 (Not Found)} if the customField is not found,
     * or with status {@code 500 (Internal Server Error)} if the customField couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CustomField> partialUpdateCustomField(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomField customField
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomField partially : {}, {}", id, customField);
        if (customField.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customField.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomField> result = customFieldRepository
            .findById(customField.getId())
            .map(existingCustomField -> {
                if (customField.getSystemName() != null) {
                    existingCustomField.setSystemName(customField.getSystemName());
                }
                if (customField.getDisplayOrder() != null) {
                    existingCustomField.setDisplayOrder(customField.getDisplayOrder());
                }
                if (customField.getName() != null) {
                    existingCustomField.setName(customField.getName());
                }
                if (customField.getValue() != null) {
                    existingCustomField.setValue(customField.getValue());
                }
                if (customField.getTechLineage() != null) {
                    existingCustomField.setTechLineage(customField.getTechLineage());
                }
                if (customField.getTechCreatedDate() != null) {
                    existingCustomField.setTechCreatedDate(customField.getTechCreatedDate());
                }
                if (customField.getTechUpdatedDate() != null) {
                    existingCustomField.setTechUpdatedDate(customField.getTechUpdatedDate());
                }
                if (customField.getTechMapping() != null) {
                    existingCustomField.setTechMapping(customField.getTechMapping());
                }
                if (customField.getTechComment() != null) {
                    existingCustomField.setTechComment(customField.getTechComment());
                }

                return existingCustomField;
            })
            .map(customFieldRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customField.getId().toString())
        );
    }

    /**
     * {@code GET  /custom-fields} : get all the customFields.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customFields in body.
     */
    @GetMapping("")
    public List<CustomField> getAllCustomFields() {
        log.debug("REST request to get all CustomFields");
        return customFieldRepository.findAll();
    }

    /**
     * {@code GET  /custom-fields/:id} : get the "id" customField.
     *
     * @param id the id of the customField to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customField, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomField> getCustomField(@PathVariable("id") Long id) {
        log.debug("REST request to get CustomField : {}", id);
        Optional<CustomField> customField = customFieldRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(customField);
    }

    /**
     * {@code DELETE  /custom-fields/:id} : delete the "id" customField.
     *
     * @param id the id of the customField to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomField(@PathVariable("id") Long id) {
        log.debug("REST request to delete CustomField : {}", id);
        customFieldRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}