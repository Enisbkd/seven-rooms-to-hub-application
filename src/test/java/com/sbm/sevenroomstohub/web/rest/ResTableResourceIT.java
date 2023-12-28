package com.sbm.sevenroomstohub.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.ResTable;
import com.sbm.sevenroomstohub.repository.ResTableRepository;
import com.sbm.sevenroomstohub.service.dto.ResTableDTO;
import com.sbm.sevenroomstohub.service.mapper.ResTableMapper;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResTableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResTableResourceIT {

    private static final Integer DEFAULT_TABLE_NUMBER = 1;
    private static final Integer UPDATED_TABLE_NUMBER = 2;

    private static final String ENTITY_API_URL = "/api/res-tables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResTableRepository resTableRepository;

    @Autowired
    private ResTableMapper resTableMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResTableMockMvc;

    private ResTable resTable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResTable createEntity(EntityManager em) {
        ResTable resTable = new ResTable().tableNumber(DEFAULT_TABLE_NUMBER);
        return resTable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResTable createUpdatedEntity(EntityManager em) {
        ResTable resTable = new ResTable().tableNumber(UPDATED_TABLE_NUMBER);
        return resTable;
    }

    @BeforeEach
    public void initTest() {
        resTable = createEntity(em);
    }

    @Test
    @Transactional
    void createResTable() throws Exception {
        int databaseSizeBeforeCreate = resTableRepository.findAll().size();
        // Create the ResTable
        ResTableDTO resTableDTO = resTableMapper.toDto(resTable);
        restResTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resTableDTO)))
            .andExpect(status().isCreated());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeCreate + 1);
        ResTable testResTable = resTableList.get(resTableList.size() - 1);
        assertThat(testResTable.getTableNumber()).isEqualTo(DEFAULT_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void createResTableWithExistingId() throws Exception {
        // Create the ResTable with an existing ID
        resTable.setId(1L);
        ResTableDTO resTableDTO = resTableMapper.toDto(resTable);

        int databaseSizeBeforeCreate = resTableRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resTableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResTables() throws Exception {
        // Initialize the database
        resTableRepository.saveAndFlush(resTable);

        // Get all the resTableList
        restResTableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resTable.getId().intValue())))
            .andExpect(jsonPath("$.[*].tableNumber").value(hasItem(DEFAULT_TABLE_NUMBER)));
    }

    @Test
    @Transactional
    void getResTable() throws Exception {
        // Initialize the database
        resTableRepository.saveAndFlush(resTable);

        // Get the resTable
        restResTableMockMvc
            .perform(get(ENTITY_API_URL_ID, resTable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resTable.getId().intValue()))
            .andExpect(jsonPath("$.tableNumber").value(DEFAULT_TABLE_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingResTable() throws Exception {
        // Get the resTable
        restResTableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResTable() throws Exception {
        // Initialize the database
        resTableRepository.saveAndFlush(resTable);

        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();

        // Update the resTable
        ResTable updatedResTable = resTableRepository.findById(resTable.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResTable are not directly saved in db
        em.detach(updatedResTable);
        updatedResTable.tableNumber(UPDATED_TABLE_NUMBER);
        ResTableDTO resTableDTO = resTableMapper.toDto(updatedResTable);

        restResTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resTableDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resTableDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
        ResTable testResTable = resTableList.get(resTableList.size() - 1);
        assertThat(testResTable.getTableNumber()).isEqualTo(UPDATED_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingResTable() throws Exception {
        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();
        resTable.setId(longCount.incrementAndGet());

        // Create the ResTable
        ResTableDTO resTableDTO = resTableMapper.toDto(resTable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resTableDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResTable() throws Exception {
        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();
        resTable.setId(longCount.incrementAndGet());

        // Create the ResTable
        ResTableDTO resTableDTO = resTableMapper.toDto(resTable);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResTable() throws Exception {
        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();
        resTable.setId(longCount.incrementAndGet());

        // Create the ResTable
        ResTableDTO resTableDTO = resTableMapper.toDto(resTable);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resTableDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResTableWithPatch() throws Exception {
        // Initialize the database
        resTableRepository.saveAndFlush(resTable);

        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();

        // Update the resTable using partial update
        ResTable partialUpdatedResTable = new ResTable();
        partialUpdatedResTable.setId(resTable.getId());

        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResTable))
            )
            .andExpect(status().isOk());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
        ResTable testResTable = resTableList.get(resTableList.size() - 1);
        assertThat(testResTable.getTableNumber()).isEqualTo(DEFAULT_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateResTableWithPatch() throws Exception {
        // Initialize the database
        resTableRepository.saveAndFlush(resTable);

        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();

        // Update the resTable using partial update
        ResTable partialUpdatedResTable = new ResTable();
        partialUpdatedResTable.setId(resTable.getId());

        partialUpdatedResTable.tableNumber(UPDATED_TABLE_NUMBER);

        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResTable))
            )
            .andExpect(status().isOk());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
        ResTable testResTable = resTableList.get(resTableList.size() - 1);
        assertThat(testResTable.getTableNumber()).isEqualTo(UPDATED_TABLE_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingResTable() throws Exception {
        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();
        resTable.setId(longCount.incrementAndGet());

        // Create the ResTable
        ResTableDTO resTableDTO = resTableMapper.toDto(resTable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resTableDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResTable() throws Exception {
        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();
        resTable.setId(longCount.incrementAndGet());

        // Create the ResTable
        ResTableDTO resTableDTO = resTableMapper.toDto(resTable);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resTableDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResTable() throws Exception {
        int databaseSizeBeforeUpdate = resTableRepository.findAll().size();
        resTable.setId(longCount.incrementAndGet());

        // Create the ResTable
        ResTableDTO resTableDTO = resTableMapper.toDto(resTable);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(resTableDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResTable in the database
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResTable() throws Exception {
        // Initialize the database
        resTableRepository.saveAndFlush(resTable);

        int databaseSizeBeforeDelete = resTableRepository.findAll().size();

        // Delete the resTable
        restResTableMockMvc
            .perform(delete(ENTITY_API_URL_ID, resTable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResTable> resTableList = resTableRepository.findAll();
        assertThat(resTableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
