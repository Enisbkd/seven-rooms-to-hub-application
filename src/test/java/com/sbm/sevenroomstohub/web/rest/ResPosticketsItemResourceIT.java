package com.sbm.sevenroomstohub.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.ResPosticketsItem;
import com.sbm.sevenroomstohub.repository.ResPosticketsItemRepository;
import com.sbm.sevenroomstohub.service.dto.ResPosticketsItemDTO;
import com.sbm.sevenroomstohub.service.mapper.ResPosticketsItemMapper;
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
 * Integration tests for the {@link ResPosticketsItemResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResPosticketsItemResourceIT {

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String ENTITY_API_URL = "/api/res-postickets-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResPosticketsItemRepository resPosticketsItemRepository;

    @Autowired
    private ResPosticketsItemMapper resPosticketsItemMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResPosticketsItemMockMvc;

    private ResPosticketsItem resPosticketsItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResPosticketsItem createEntity(EntityManager em) {
        ResPosticketsItem resPosticketsItem = new ResPosticketsItem().price(DEFAULT_PRICE).name(DEFAULT_NAME).quantity(DEFAULT_QUANTITY);
        return resPosticketsItem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResPosticketsItem createUpdatedEntity(EntityManager em) {
        ResPosticketsItem resPosticketsItem = new ResPosticketsItem().price(UPDATED_PRICE).name(UPDATED_NAME).quantity(UPDATED_QUANTITY);
        return resPosticketsItem;
    }

    @BeforeEach
    public void initTest() {
        resPosticketsItem = createEntity(em);
    }

    @Test
    @Transactional
    void createResPosticketsItem() throws Exception {
        int databaseSizeBeforeCreate = resPosticketsItemRepository.findAll().size();
        // Create the ResPosticketsItem
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(resPosticketsItem);
        restResPosticketsItemMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeCreate + 1);
        ResPosticketsItem testResPosticketsItem = resPosticketsItemList.get(resPosticketsItemList.size() - 1);
        assertThat(testResPosticketsItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testResPosticketsItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testResPosticketsItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void createResPosticketsItemWithExistingId() throws Exception {
        // Create the ResPosticketsItem with an existing ID
        resPosticketsItem.setId(1L);
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(resPosticketsItem);

        int databaseSizeBeforeCreate = resPosticketsItemRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResPosticketsItemMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResPosticketsItems() throws Exception {
        // Initialize the database
        resPosticketsItemRepository.saveAndFlush(resPosticketsItem);

        // Get all the resPosticketsItemList
        restResPosticketsItemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resPosticketsItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
    }

    @Test
    @Transactional
    void getResPosticketsItem() throws Exception {
        // Initialize the database
        resPosticketsItemRepository.saveAndFlush(resPosticketsItem);

        // Get the resPosticketsItem
        restResPosticketsItemMockMvc
            .perform(get(ENTITY_API_URL_ID, resPosticketsItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resPosticketsItem.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }

    @Test
    @Transactional
    void getNonExistingResPosticketsItem() throws Exception {
        // Get the resPosticketsItem
        restResPosticketsItemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResPosticketsItem() throws Exception {
        // Initialize the database
        resPosticketsItemRepository.saveAndFlush(resPosticketsItem);

        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();

        // Update the resPosticketsItem
        ResPosticketsItem updatedResPosticketsItem = resPosticketsItemRepository.findById(resPosticketsItem.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResPosticketsItem are not directly saved in db
        em.detach(updatedResPosticketsItem);
        updatedResPosticketsItem.price(UPDATED_PRICE).name(UPDATED_NAME).quantity(UPDATED_QUANTITY);
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(updatedResPosticketsItem);

        restResPosticketsItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resPosticketsItemDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
        ResPosticketsItem testResPosticketsItem = resPosticketsItemList.get(resPosticketsItemList.size() - 1);
        assertThat(testResPosticketsItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testResPosticketsItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResPosticketsItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void putNonExistingResPosticketsItem() throws Exception {
        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();
        resPosticketsItem.setId(longCount.incrementAndGet());

        // Create the ResPosticketsItem
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(resPosticketsItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResPosticketsItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resPosticketsItemDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResPosticketsItem() throws Exception {
        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();
        resPosticketsItem.setId(longCount.incrementAndGet());

        // Create the ResPosticketsItem
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(resPosticketsItem);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosticketsItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResPosticketsItem() throws Exception {
        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();
        resPosticketsItem.setId(longCount.incrementAndGet());

        // Create the ResPosticketsItem
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(resPosticketsItem);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosticketsItemMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResPosticketsItemWithPatch() throws Exception {
        // Initialize the database
        resPosticketsItemRepository.saveAndFlush(resPosticketsItem);

        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();

        // Update the resPosticketsItem using partial update
        ResPosticketsItem partialUpdatedResPosticketsItem = new ResPosticketsItem();
        partialUpdatedResPosticketsItem.setId(resPosticketsItem.getId());

        partialUpdatedResPosticketsItem.name(UPDATED_NAME);

        restResPosticketsItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResPosticketsItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResPosticketsItem))
            )
            .andExpect(status().isOk());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
        ResPosticketsItem testResPosticketsItem = resPosticketsItemList.get(resPosticketsItemList.size() - 1);
        assertThat(testResPosticketsItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testResPosticketsItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResPosticketsItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void fullUpdateResPosticketsItemWithPatch() throws Exception {
        // Initialize the database
        resPosticketsItemRepository.saveAndFlush(resPosticketsItem);

        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();

        // Update the resPosticketsItem using partial update
        ResPosticketsItem partialUpdatedResPosticketsItem = new ResPosticketsItem();
        partialUpdatedResPosticketsItem.setId(resPosticketsItem.getId());

        partialUpdatedResPosticketsItem.price(UPDATED_PRICE).name(UPDATED_NAME).quantity(UPDATED_QUANTITY);

        restResPosticketsItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResPosticketsItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedResPosticketsItem))
            )
            .andExpect(status().isOk());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
        ResPosticketsItem testResPosticketsItem = resPosticketsItemList.get(resPosticketsItemList.size() - 1);
        assertThat(testResPosticketsItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testResPosticketsItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResPosticketsItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void patchNonExistingResPosticketsItem() throws Exception {
        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();
        resPosticketsItem.setId(longCount.incrementAndGet());

        // Create the ResPosticketsItem
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(resPosticketsItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResPosticketsItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resPosticketsItemDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResPosticketsItem() throws Exception {
        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();
        resPosticketsItem.setId(longCount.incrementAndGet());

        // Create the ResPosticketsItem
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(resPosticketsItem);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosticketsItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResPosticketsItem() throws Exception {
        int databaseSizeBeforeUpdate = resPosticketsItemRepository.findAll().size();
        resPosticketsItem.setId(longCount.incrementAndGet());

        // Create the ResPosticketsItem
        ResPosticketsItemDTO resPosticketsItemDTO = resPosticketsItemMapper.toDto(resPosticketsItem);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosticketsItemMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(resPosticketsItemDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResPosticketsItem in the database
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResPosticketsItem() throws Exception {
        // Initialize the database
        resPosticketsItemRepository.saveAndFlush(resPosticketsItem);

        int databaseSizeBeforeDelete = resPosticketsItemRepository.findAll().size();

        // Delete the resPosticketsItem
        restResPosticketsItemMockMvc
            .perform(delete(ENTITY_API_URL_ID, resPosticketsItem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResPosticketsItem> resPosticketsItemList = resPosticketsItemRepository.findAll();
        assertThat(resPosticketsItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
