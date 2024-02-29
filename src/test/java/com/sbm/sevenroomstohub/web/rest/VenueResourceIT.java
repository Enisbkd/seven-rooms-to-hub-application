package com.sbm.sevenroomstohub.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sbm.sevenroomstohub.IntegrationTest;
import com.sbm.sevenroomstohub.domain.Venue;
import com.sbm.sevenroomstohub.repository.VenueRepository;
import com.sbm.sevenroomstohub.service.dto.VenueDTO;
import com.sbm.sevenroomstohub.service.mapper.VenueMapper;
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
 * Integration tests for the {@link VenueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VenueResourceIT {

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_BLACK_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_BLACK_LOGO = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CROSS_STREET = "AAAAAAAAAA";
    private static final String UPDATED_CROSS_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_VENUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_VENUE_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FULL_DINING_BACKEND = false;
    private static final Boolean UPDATED_FULL_DINING_BACKEND = true;

    private static final Boolean DEFAULT_GRID_ENABLED = false;
    private static final Boolean UPDATED_GRID_ENABLED = true;

    private static final String DEFAULT_VENUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MEMBERSHIP_ENABLED = false;
    private static final Boolean UPDATED_MEMBERSHIP_ENABLED = true;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NEIGHBORHOOD = "AAAAAAAAAA";
    private static final String UPDATED_NEIGHBORHOOD = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_POLICY = "AAAAAAAAAA";
    private static final String UPDATED_POLICY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIQUE_CONFIRMATION_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUE_CONFIRMATION_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_URL_KEY = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_URL_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_WHITE_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_WHITE_LOGO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/venues";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private VenueMapper venueMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVenueMockMvc;

    private Venue venue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Venue createEntity(EntityManager em) {
        Venue venue = new Venue()
            .address(DEFAULT_ADDRESS)
            .blackLogo(DEFAULT_BLACK_LOGO)
            .country(DEFAULT_COUNTRY)
            .crossStreet(DEFAULT_CROSS_STREET)
            .currencyCode(DEFAULT_CURRENCY_CODE)
            .externalVenueId(DEFAULT_EXTERNAL_VENUE_ID)
            .fullDiningBackend(DEFAULT_FULL_DINING_BACKEND)
            .gridEnabled(DEFAULT_GRID_ENABLED)
            .venueId(DEFAULT_VENUE_ID)
            .internalName(DEFAULT_INTERNAL_NAME)
            .membershipEnabled(DEFAULT_MEMBERSHIP_ENABLED)
            .name(DEFAULT_NAME)
            .neighborhood(DEFAULT_NEIGHBORHOOD)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .policy(DEFAULT_POLICY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .primaryColor(DEFAULT_PRIMARY_COLOR)
            .secondaryColor(DEFAULT_SECONDARY_COLOR)
            .state(DEFAULT_STATE)
            .uniqueConfirmationPrefix(DEFAULT_UNIQUE_CONFIRMATION_PREFIX)
            .venueClass(DEFAULT_VENUE_CLASS)
            .venueGroupId(DEFAULT_VENUE_GROUP_ID)
            .venueGroupName(DEFAULT_VENUE_GROUP_NAME)
            .venueUrlKey(DEFAULT_VENUE_URL_KEY)
            .website(DEFAULT_WEBSITE)
            .whiteLogo(DEFAULT_WHITE_LOGO);
        return venue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Venue createUpdatedEntity(EntityManager em) {
        Venue venue = new Venue()
            .address(UPDATED_ADDRESS)
            .blackLogo(UPDATED_BLACK_LOGO)
            .country(UPDATED_COUNTRY)
            .crossStreet(UPDATED_CROSS_STREET)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .externalVenueId(UPDATED_EXTERNAL_VENUE_ID)
            .fullDiningBackend(UPDATED_FULL_DINING_BACKEND)
            .gridEnabled(UPDATED_GRID_ENABLED)
            .venueId(UPDATED_VENUE_ID)
            .internalName(UPDATED_INTERNAL_NAME)
            .membershipEnabled(UPDATED_MEMBERSHIP_ENABLED)
            .name(UPDATED_NAME)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .policy(UPDATED_POLICY)
            .postalCode(UPDATED_POSTAL_CODE)
            .primaryColor(UPDATED_PRIMARY_COLOR)
            .secondaryColor(UPDATED_SECONDARY_COLOR)
            .state(UPDATED_STATE)
            .uniqueConfirmationPrefix(UPDATED_UNIQUE_CONFIRMATION_PREFIX)
            .venueClass(UPDATED_VENUE_CLASS)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueGroupName(UPDATED_VENUE_GROUP_NAME)
            .venueUrlKey(UPDATED_VENUE_URL_KEY)
            .website(UPDATED_WEBSITE)
            .whiteLogo(UPDATED_WHITE_LOGO);
        return venue;
    }

    @BeforeEach
    public void initTest() {
        venue = createEntity(em);
    }

    @Test
    @Transactional
    void createVenue() throws Exception {
        int databaseSizeBeforeCreate = venueRepository.findAll().size();
        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);
        restVenueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(venueDTO)))
            .andExpect(status().isCreated());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeCreate + 1);
        Venue testVenue = venueList.get(venueList.size() - 1);
        assertThat(testVenue.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testVenue.getBlackLogo()).isEqualTo(DEFAULT_BLACK_LOGO);
        assertThat(testVenue.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testVenue.getCrossStreet()).isEqualTo(DEFAULT_CROSS_STREET);
        assertThat(testVenue.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testVenue.getExternalVenueId()).isEqualTo(DEFAULT_EXTERNAL_VENUE_ID);
        assertThat(testVenue.getFullDiningBackend()).isEqualTo(DEFAULT_FULL_DINING_BACKEND);
        assertThat(testVenue.getGridEnabled()).isEqualTo(DEFAULT_GRID_ENABLED);
        assertThat(testVenue.getVenueId()).isEqualTo(DEFAULT_VENUE_ID);
        assertThat(testVenue.getInternalName()).isEqualTo(DEFAULT_INTERNAL_NAME);
        assertThat(testVenue.getMembershipEnabled()).isEqualTo(DEFAULT_MEMBERSHIP_ENABLED);
        assertThat(testVenue.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVenue.getNeighborhood()).isEqualTo(DEFAULT_NEIGHBORHOOD);
        assertThat(testVenue.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testVenue.getPolicy()).isEqualTo(DEFAULT_POLICY);
        assertThat(testVenue.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testVenue.getPrimaryColor()).isEqualTo(DEFAULT_PRIMARY_COLOR);
        assertThat(testVenue.getSecondaryColor()).isEqualTo(DEFAULT_SECONDARY_COLOR);
        assertThat(testVenue.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testVenue.getUniqueConfirmationPrefix()).isEqualTo(DEFAULT_UNIQUE_CONFIRMATION_PREFIX);
        assertThat(testVenue.getVenueClass()).isEqualTo(DEFAULT_VENUE_CLASS);
        assertThat(testVenue.getVenueGroupId()).isEqualTo(DEFAULT_VENUE_GROUP_ID);
        assertThat(testVenue.getVenueGroupName()).isEqualTo(DEFAULT_VENUE_GROUP_NAME);
        assertThat(testVenue.getVenueUrlKey()).isEqualTo(DEFAULT_VENUE_URL_KEY);
        assertThat(testVenue.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testVenue.getWhiteLogo()).isEqualTo(DEFAULT_WHITE_LOGO);
    }

    @Test
    @Transactional
    void createVenueWithExistingId() throws Exception {
        // Create the Venue with an existing ID
        venue.setId(1L);
        VenueDTO venueDTO = venueMapper.toDto(venue);

        int databaseSizeBeforeCreate = venueRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVenueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(venueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVenues() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get all the venueList
        restVenueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(venue.getId().intValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].blackLogo").value(hasItem(DEFAULT_BLACK_LOGO)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].crossStreet").value(hasItem(DEFAULT_CROSS_STREET)))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].externalVenueId").value(hasItem(DEFAULT_EXTERNAL_VENUE_ID)))
            .andExpect(jsonPath("$.[*].fullDiningBackend").value(hasItem(DEFAULT_FULL_DINING_BACKEND.booleanValue())))
            .andExpect(jsonPath("$.[*].gridEnabled").value(hasItem(DEFAULT_GRID_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].venueId").value(hasItem(DEFAULT_VENUE_ID)))
            .andExpect(jsonPath("$.[*].internalName").value(hasItem(DEFAULT_INTERNAL_NAME)))
            .andExpect(jsonPath("$.[*].membershipEnabled").value(hasItem(DEFAULT_MEMBERSHIP_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].neighborhood").value(hasItem(DEFAULT_NEIGHBORHOOD)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].policy").value(hasItem(DEFAULT_POLICY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].primaryColor").value(hasItem(DEFAULT_PRIMARY_COLOR)))
            .andExpect(jsonPath("$.[*].secondaryColor").value(hasItem(DEFAULT_SECONDARY_COLOR)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].uniqueConfirmationPrefix").value(hasItem(DEFAULT_UNIQUE_CONFIRMATION_PREFIX)))
            .andExpect(jsonPath("$.[*].venueClass").value(hasItem(DEFAULT_VENUE_CLASS)))
            .andExpect(jsonPath("$.[*].venueGroupId").value(hasItem(DEFAULT_VENUE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].venueGroupName").value(hasItem(DEFAULT_VENUE_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].venueUrlKey").value(hasItem(DEFAULT_VENUE_URL_KEY)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].whiteLogo").value(hasItem(DEFAULT_WHITE_LOGO)));
    }

    @Test
    @Transactional
    void getVenue() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get the venue
        restVenueMockMvc
            .perform(get(ENTITY_API_URL_ID, venue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(venue.getId().intValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.blackLogo").value(DEFAULT_BLACK_LOGO))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.crossStreet").value(DEFAULT_CROSS_STREET))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE))
            .andExpect(jsonPath("$.externalVenueId").value(DEFAULT_EXTERNAL_VENUE_ID))
            .andExpect(jsonPath("$.fullDiningBackend").value(DEFAULT_FULL_DINING_BACKEND.booleanValue()))
            .andExpect(jsonPath("$.gridEnabled").value(DEFAULT_GRID_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.venueId").value(DEFAULT_VENUE_ID))
            .andExpect(jsonPath("$.internalName").value(DEFAULT_INTERNAL_NAME))
            .andExpect(jsonPath("$.membershipEnabled").value(DEFAULT_MEMBERSHIP_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.neighborhood").value(DEFAULT_NEIGHBORHOOD))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.policy").value(DEFAULT_POLICY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.primaryColor").value(DEFAULT_PRIMARY_COLOR))
            .andExpect(jsonPath("$.secondaryColor").value(DEFAULT_SECONDARY_COLOR))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.uniqueConfirmationPrefix").value(DEFAULT_UNIQUE_CONFIRMATION_PREFIX))
            .andExpect(jsonPath("$.venueClass").value(DEFAULT_VENUE_CLASS))
            .andExpect(jsonPath("$.venueGroupId").value(DEFAULT_VENUE_GROUP_ID))
            .andExpect(jsonPath("$.venueGroupName").value(DEFAULT_VENUE_GROUP_NAME))
            .andExpect(jsonPath("$.venueUrlKey").value(DEFAULT_VENUE_URL_KEY))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.whiteLogo").value(DEFAULT_WHITE_LOGO));
    }

    @Test
    @Transactional
    void getNonExistingVenue() throws Exception {
        // Get the venue
        restVenueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVenue() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        int databaseSizeBeforeUpdate = venueRepository.findAll().size();

        // Update the venue
        Venue updatedVenue = venueRepository.findById(venue.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVenue are not directly saved in db
        em.detach(updatedVenue);
        updatedVenue
            .address(UPDATED_ADDRESS)
            .blackLogo(UPDATED_BLACK_LOGO)
            .country(UPDATED_COUNTRY)
            .crossStreet(UPDATED_CROSS_STREET)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .externalVenueId(UPDATED_EXTERNAL_VENUE_ID)
            .fullDiningBackend(UPDATED_FULL_DINING_BACKEND)
            .gridEnabled(UPDATED_GRID_ENABLED)
            .venueId(UPDATED_VENUE_ID)
            .internalName(UPDATED_INTERNAL_NAME)
            .membershipEnabled(UPDATED_MEMBERSHIP_ENABLED)
            .name(UPDATED_NAME)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .policy(UPDATED_POLICY)
            .postalCode(UPDATED_POSTAL_CODE)
            .primaryColor(UPDATED_PRIMARY_COLOR)
            .secondaryColor(UPDATED_SECONDARY_COLOR)
            .state(UPDATED_STATE)
            .uniqueConfirmationPrefix(UPDATED_UNIQUE_CONFIRMATION_PREFIX)
            .venueClass(UPDATED_VENUE_CLASS)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueGroupName(UPDATED_VENUE_GROUP_NAME)
            .venueUrlKey(UPDATED_VENUE_URL_KEY)
            .website(UPDATED_WEBSITE)
            .whiteLogo(UPDATED_WHITE_LOGO);
        VenueDTO venueDTO = venueMapper.toDto(updatedVenue);

        restVenueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, venueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(venueDTO))
            )
            .andExpect(status().isOk());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
        Venue testVenue = venueList.get(venueList.size() - 1);
        assertThat(testVenue.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testVenue.getBlackLogo()).isEqualTo(UPDATED_BLACK_LOGO);
        assertThat(testVenue.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testVenue.getCrossStreet()).isEqualTo(UPDATED_CROSS_STREET);
        assertThat(testVenue.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testVenue.getExternalVenueId()).isEqualTo(UPDATED_EXTERNAL_VENUE_ID);
        assertThat(testVenue.getFullDiningBackend()).isEqualTo(UPDATED_FULL_DINING_BACKEND);
        assertThat(testVenue.getGridEnabled()).isEqualTo(UPDATED_GRID_ENABLED);
        assertThat(testVenue.getVenueId()).isEqualTo(UPDATED_VENUE_ID);
        assertThat(testVenue.getInternalName()).isEqualTo(UPDATED_INTERNAL_NAME);
        assertThat(testVenue.getMembershipEnabled()).isEqualTo(UPDATED_MEMBERSHIP_ENABLED);
        assertThat(testVenue.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVenue.getNeighborhood()).isEqualTo(UPDATED_NEIGHBORHOOD);
        assertThat(testVenue.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testVenue.getPolicy()).isEqualTo(UPDATED_POLICY);
        assertThat(testVenue.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testVenue.getPrimaryColor()).isEqualTo(UPDATED_PRIMARY_COLOR);
        assertThat(testVenue.getSecondaryColor()).isEqualTo(UPDATED_SECONDARY_COLOR);
        assertThat(testVenue.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testVenue.getUniqueConfirmationPrefix()).isEqualTo(UPDATED_UNIQUE_CONFIRMATION_PREFIX);
        assertThat(testVenue.getVenueClass()).isEqualTo(UPDATED_VENUE_CLASS);
        assertThat(testVenue.getVenueGroupId()).isEqualTo(UPDATED_VENUE_GROUP_ID);
        assertThat(testVenue.getVenueGroupName()).isEqualTo(UPDATED_VENUE_GROUP_NAME);
        assertThat(testVenue.getVenueUrlKey()).isEqualTo(UPDATED_VENUE_URL_KEY);
        assertThat(testVenue.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testVenue.getWhiteLogo()).isEqualTo(UPDATED_WHITE_LOGO);
    }

    @Test
    @Transactional
    void putNonExistingVenue() throws Exception {
        int databaseSizeBeforeUpdate = venueRepository.findAll().size();
        venue.setId(longCount.incrementAndGet());

        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, venueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(venueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVenue() throws Exception {
        int databaseSizeBeforeUpdate = venueRepository.findAll().size();
        venue.setId(longCount.incrementAndGet());

        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(venueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVenue() throws Exception {
        int databaseSizeBeforeUpdate = venueRepository.findAll().size();
        venue.setId(longCount.incrementAndGet());

        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(venueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVenueWithPatch() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        int databaseSizeBeforeUpdate = venueRepository.findAll().size();

        // Update the venue using partial update
        Venue partialUpdatedVenue = new Venue();
        partialUpdatedVenue.setId(venue.getId());

        partialUpdatedVenue
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .externalVenueId(UPDATED_EXTERNAL_VENUE_ID)
            .fullDiningBackend(UPDATED_FULL_DINING_BACKEND)
            .gridEnabled(UPDATED_GRID_ENABLED)
            .membershipEnabled(UPDATED_MEMBERSHIP_ENABLED)
            .policy(UPDATED_POLICY)
            .primaryColor(UPDATED_PRIMARY_COLOR)
            .secondaryColor(UPDATED_SECONDARY_COLOR)
            .venueClass(UPDATED_VENUE_CLASS)
            .venueUrlKey(UPDATED_VENUE_URL_KEY)
            .website(UPDATED_WEBSITE);

        restVenueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVenue.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVenue))
            )
            .andExpect(status().isOk());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
        Venue testVenue = venueList.get(venueList.size() - 1);
        assertThat(testVenue.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testVenue.getBlackLogo()).isEqualTo(DEFAULT_BLACK_LOGO);
        assertThat(testVenue.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testVenue.getCrossStreet()).isEqualTo(DEFAULT_CROSS_STREET);
        assertThat(testVenue.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testVenue.getExternalVenueId()).isEqualTo(UPDATED_EXTERNAL_VENUE_ID);
        assertThat(testVenue.getFullDiningBackend()).isEqualTo(UPDATED_FULL_DINING_BACKEND);
        assertThat(testVenue.getGridEnabled()).isEqualTo(UPDATED_GRID_ENABLED);
        assertThat(testVenue.getVenueId()).isEqualTo(DEFAULT_VENUE_ID);
        assertThat(testVenue.getInternalName()).isEqualTo(DEFAULT_INTERNAL_NAME);
        assertThat(testVenue.getMembershipEnabled()).isEqualTo(UPDATED_MEMBERSHIP_ENABLED);
        assertThat(testVenue.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVenue.getNeighborhood()).isEqualTo(DEFAULT_NEIGHBORHOOD);
        assertThat(testVenue.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testVenue.getPolicy()).isEqualTo(UPDATED_POLICY);
        assertThat(testVenue.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testVenue.getPrimaryColor()).isEqualTo(UPDATED_PRIMARY_COLOR);
        assertThat(testVenue.getSecondaryColor()).isEqualTo(UPDATED_SECONDARY_COLOR);
        assertThat(testVenue.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testVenue.getUniqueConfirmationPrefix()).isEqualTo(DEFAULT_UNIQUE_CONFIRMATION_PREFIX);
        assertThat(testVenue.getVenueClass()).isEqualTo(UPDATED_VENUE_CLASS);
        assertThat(testVenue.getVenueGroupId()).isEqualTo(DEFAULT_VENUE_GROUP_ID);
        assertThat(testVenue.getVenueGroupName()).isEqualTo(DEFAULT_VENUE_GROUP_NAME);
        assertThat(testVenue.getVenueUrlKey()).isEqualTo(UPDATED_VENUE_URL_KEY);
        assertThat(testVenue.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testVenue.getWhiteLogo()).isEqualTo(DEFAULT_WHITE_LOGO);
    }

    @Test
    @Transactional
    void fullUpdateVenueWithPatch() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        int databaseSizeBeforeUpdate = venueRepository.findAll().size();

        // Update the venue using partial update
        Venue partialUpdatedVenue = new Venue();
        partialUpdatedVenue.setId(venue.getId());

        partialUpdatedVenue
            .address(UPDATED_ADDRESS)
            .blackLogo(UPDATED_BLACK_LOGO)
            .country(UPDATED_COUNTRY)
            .crossStreet(UPDATED_CROSS_STREET)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .externalVenueId(UPDATED_EXTERNAL_VENUE_ID)
            .fullDiningBackend(UPDATED_FULL_DINING_BACKEND)
            .gridEnabled(UPDATED_GRID_ENABLED)
            .venueId(UPDATED_VENUE_ID)
            .internalName(UPDATED_INTERNAL_NAME)
            .membershipEnabled(UPDATED_MEMBERSHIP_ENABLED)
            .name(UPDATED_NAME)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .policy(UPDATED_POLICY)
            .postalCode(UPDATED_POSTAL_CODE)
            .primaryColor(UPDATED_PRIMARY_COLOR)
            .secondaryColor(UPDATED_SECONDARY_COLOR)
            .state(UPDATED_STATE)
            .uniqueConfirmationPrefix(UPDATED_UNIQUE_CONFIRMATION_PREFIX)
            .venueClass(UPDATED_VENUE_CLASS)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueGroupName(UPDATED_VENUE_GROUP_NAME)
            .venueUrlKey(UPDATED_VENUE_URL_KEY)
            .website(UPDATED_WEBSITE)
            .whiteLogo(UPDATED_WHITE_LOGO);

        restVenueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVenue.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVenue))
            )
            .andExpect(status().isOk());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
        Venue testVenue = venueList.get(venueList.size() - 1);
        assertThat(testVenue.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testVenue.getBlackLogo()).isEqualTo(UPDATED_BLACK_LOGO);
        assertThat(testVenue.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testVenue.getCrossStreet()).isEqualTo(UPDATED_CROSS_STREET);
        assertThat(testVenue.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testVenue.getExternalVenueId()).isEqualTo(UPDATED_EXTERNAL_VENUE_ID);
        assertThat(testVenue.getFullDiningBackend()).isEqualTo(UPDATED_FULL_DINING_BACKEND);
        assertThat(testVenue.getGridEnabled()).isEqualTo(UPDATED_GRID_ENABLED);
        assertThat(testVenue.getVenueId()).isEqualTo(UPDATED_VENUE_ID);
        assertThat(testVenue.getInternalName()).isEqualTo(UPDATED_INTERNAL_NAME);
        assertThat(testVenue.getMembershipEnabled()).isEqualTo(UPDATED_MEMBERSHIP_ENABLED);
        assertThat(testVenue.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVenue.getNeighborhood()).isEqualTo(UPDATED_NEIGHBORHOOD);
        assertThat(testVenue.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testVenue.getPolicy()).isEqualTo(UPDATED_POLICY);
        assertThat(testVenue.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testVenue.getPrimaryColor()).isEqualTo(UPDATED_PRIMARY_COLOR);
        assertThat(testVenue.getSecondaryColor()).isEqualTo(UPDATED_SECONDARY_COLOR);
        assertThat(testVenue.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testVenue.getUniqueConfirmationPrefix()).isEqualTo(UPDATED_UNIQUE_CONFIRMATION_PREFIX);
        assertThat(testVenue.getVenueClass()).isEqualTo(UPDATED_VENUE_CLASS);
        assertThat(testVenue.getVenueGroupId()).isEqualTo(UPDATED_VENUE_GROUP_ID);
        assertThat(testVenue.getVenueGroupName()).isEqualTo(UPDATED_VENUE_GROUP_NAME);
        assertThat(testVenue.getVenueUrlKey()).isEqualTo(UPDATED_VENUE_URL_KEY);
        assertThat(testVenue.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testVenue.getWhiteLogo()).isEqualTo(UPDATED_WHITE_LOGO);
    }

    @Test
    @Transactional
    void patchNonExistingVenue() throws Exception {
        int databaseSizeBeforeUpdate = venueRepository.findAll().size();
        venue.setId(longCount.incrementAndGet());

        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, venueDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(venueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVenue() throws Exception {
        int databaseSizeBeforeUpdate = venueRepository.findAll().size();
        venue.setId(longCount.incrementAndGet());

        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(venueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVenue() throws Exception {
        int databaseSizeBeforeUpdate = venueRepository.findAll().size();
        venue.setId(longCount.incrementAndGet());

        // Create the Venue
        VenueDTO venueDTO = venueMapper.toDto(venue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(venueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVenue() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        int databaseSizeBeforeDelete = venueRepository.findAll().size();

        // Delete the venue
        restVenueMockMvc
            .perform(delete(ENTITY_API_URL_ID, venue.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
