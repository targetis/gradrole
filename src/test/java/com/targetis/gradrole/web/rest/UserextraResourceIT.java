package com.targetis.gradrole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.targetis.gradrole.IntegrationTest;
import com.targetis.gradrole.domain.User;
import com.targetis.gradrole.domain.Userextra;
import com.targetis.gradrole.repository.UserRepository;
import com.targetis.gradrole.repository.UserextraRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserextraResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserextraResourceIT {

    private static final String DEFAULT_MIDDLENAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLENAME = "BBBBBBBBBB";

    private static final String DEFAULT_JOBROLE = "AAAAAAAAAA";
    private static final String UPDATED_JOBROLE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UserextraRepository userextraRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserextraMockMvc;

    private Userextra userextra;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userextra createEntity(EntityManager em) {
        Userextra userextra = new Userextra().middlename(DEFAULT_MIDDLENAME).jobrole(DEFAULT_JOBROLE).dob(DEFAULT_DOB);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        userextra.setUser(user);
        return userextra;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userextra createUpdatedEntity(EntityManager em) {
        Userextra userextra = new Userextra().middlename(UPDATED_MIDDLENAME).jobrole(UPDATED_JOBROLE).dob(UPDATED_DOB);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        userextra.setUser(user);
        return userextra;
    }

    @BeforeEach
    public void initTest() {
        userextra = createEntity(em);
    }

    @Test
    @Transactional
    void createUserextra() throws Exception {
        int databaseSizeBeforeCreate = userextraRepository.findAll().size();
        // Create the Userextra
        restUserextraMockMvc
            .perform(
                post("/api/userextras")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userextra))
            )
            .andExpect(status().isCreated());

        // Validate the Userextra in the database
        List<Userextra> userextraList = userextraRepository.findAll();
        assertThat(userextraList).hasSize(databaseSizeBeforeCreate + 1);
        Userextra testUserextra = userextraList.get(userextraList.size() - 1);
        assertThat(testUserextra.getMiddlename()).isEqualTo(DEFAULT_MIDDLENAME);
        assertThat(testUserextra.getJobrole()).isEqualTo(DEFAULT_JOBROLE);
        assertThat(testUserextra.getDob()).isEqualTo(DEFAULT_DOB);

        // Validate the id for MapsId, the ids must be same
        assertThat(testUserextra.getId()).isEqualTo(testUserextra.getUser().getId());
    }

    @Test
    @Transactional
    void createUserextraWithExistingId() throws Exception {
        // Create the Userextra with an existing ID
        userextra.setId(1L);

        int databaseSizeBeforeCreate = userextraRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserextraMockMvc
            .perform(
                post("/api/userextras")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userextra))
            )
            .andExpect(status().isBadRequest());

        // Validate the Userextra in the database
        List<Userextra> userextraList = userextraRepository.findAll();
        assertThat(userextraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void updateUserextraMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        userextraRepository.saveAndFlush(userextra);
        int databaseSizeBeforeCreate = userextraRepository.findAll().size();

        // Add a new parent entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();

        // Load the userextra
        Userextra updatedUserextra = userextraRepository.findById(userextra.getId()).get();
        assertThat(updatedUserextra).isNotNull();
        // Disconnect from session so that the updates on updatedUserextra are not directly saved in db
        em.detach(updatedUserextra);

        // Update the User with new association value
        updatedUserextra.setUser(user);

        // Update the entity
        restUserextraMockMvc
            .perform(
                put("/api/userextras")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUserextra))
            )
            .andExpect(status().isOk());

        // Validate the Userextra in the database
        List<Userextra> userextraList = userextraRepository.findAll();
        assertThat(userextraList).hasSize(databaseSizeBeforeCreate);
        Userextra testUserextra = userextraList.get(userextraList.size() - 1);
        // Validate the id for MapsId, the ids must be same
        // Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
        // Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
        // assertThat(testUserextra.getId()).isEqualTo(testUserextra.getUser().getId());
    }

    @Test
    @Transactional
    void getAllUserextras() throws Exception {
        // Initialize the database
        userextraRepository.saveAndFlush(userextra);

        // Get all the userextraList
        restUserextraMockMvc
            .perform(get("/api/userextras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userextra.getId().intValue())))
            .andExpect(jsonPath("$.[*].middlename").value(hasItem(DEFAULT_MIDDLENAME)))
            .andExpect(jsonPath("$.[*].jobrole").value(hasItem(DEFAULT_JOBROLE)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())));
    }

    @Test
    @Transactional
    void getUserextra() throws Exception {
        // Initialize the database
        userextraRepository.saveAndFlush(userextra);

        // Get the userextra
        restUserextraMockMvc
            .perform(get("/api/userextras/{id}", userextra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userextra.getId().intValue()))
            .andExpect(jsonPath("$.middlename").value(DEFAULT_MIDDLENAME))
            .andExpect(jsonPath("$.jobrole").value(DEFAULT_JOBROLE))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()));
    }

    @Test
    @Transactional
    void getNonExistingUserextra() throws Exception {
        // Get the userextra
        restUserextraMockMvc.perform(get("/api/userextras/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateUserextra() throws Exception {
        // Initialize the database
        userextraRepository.saveAndFlush(userextra);

        int databaseSizeBeforeUpdate = userextraRepository.findAll().size();

        // Update the userextra
        Userextra updatedUserextra = userextraRepository.findById(userextra.getId()).get();
        // Disconnect from session so that the updates on updatedUserextra are not directly saved in db
        em.detach(updatedUserextra);
        updatedUserextra.middlename(UPDATED_MIDDLENAME).jobrole(UPDATED_JOBROLE).dob(UPDATED_DOB);

        restUserextraMockMvc
            .perform(
                put("/api/userextras")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUserextra))
            )
            .andExpect(status().isOk());

        // Validate the Userextra in the database
        List<Userextra> userextraList = userextraRepository.findAll();
        assertThat(userextraList).hasSize(databaseSizeBeforeUpdate);
        Userextra testUserextra = userextraList.get(userextraList.size() - 1);
        assertThat(testUserextra.getMiddlename()).isEqualTo(UPDATED_MIDDLENAME);
        assertThat(testUserextra.getJobrole()).isEqualTo(UPDATED_JOBROLE);
        assertThat(testUserextra.getDob()).isEqualTo(UPDATED_DOB);
    }

    @Test
    @Transactional
    void updateNonExistingUserextra() throws Exception {
        int databaseSizeBeforeUpdate = userextraRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserextraMockMvc
            .perform(
                put("/api/userextras")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userextra))
            )
            .andExpect(status().isBadRequest());

        // Validate the Userextra in the database
        List<Userextra> userextraList = userextraRepository.findAll();
        assertThat(userextraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserextraWithPatch() throws Exception {
        // Initialize the database
        userextraRepository.saveAndFlush(userextra);

        int databaseSizeBeforeUpdate = userextraRepository.findAll().size();

        // Update the userextra using partial update
        Userextra partialUpdatedUserextra = new Userextra();
        partialUpdatedUserextra.setId(userextra.getId());

        partialUpdatedUserextra.jobrole(UPDATED_JOBROLE);

        restUserextraMockMvc
            .perform(
                patch("/api/userextras")
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserextra))
            )
            .andExpect(status().isOk());

        // Validate the Userextra in the database
        List<Userextra> userextraList = userextraRepository.findAll();
        assertThat(userextraList).hasSize(databaseSizeBeforeUpdate);
        Userextra testUserextra = userextraList.get(userextraList.size() - 1);
        assertThat(testUserextra.getMiddlename()).isEqualTo(DEFAULT_MIDDLENAME);
        assertThat(testUserextra.getJobrole()).isEqualTo(UPDATED_JOBROLE);
        assertThat(testUserextra.getDob()).isEqualTo(DEFAULT_DOB);
    }

    @Test
    @Transactional
    void fullUpdateUserextraWithPatch() throws Exception {
        // Initialize the database
        userextraRepository.saveAndFlush(userextra);

        int databaseSizeBeforeUpdate = userextraRepository.findAll().size();

        // Update the userextra using partial update
        Userextra partialUpdatedUserextra = new Userextra();
        partialUpdatedUserextra.setId(userextra.getId());

        partialUpdatedUserextra.middlename(UPDATED_MIDDLENAME).jobrole(UPDATED_JOBROLE).dob(UPDATED_DOB);

        restUserextraMockMvc
            .perform(
                patch("/api/userextras")
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserextra))
            )
            .andExpect(status().isOk());

        // Validate the Userextra in the database
        List<Userextra> userextraList = userextraRepository.findAll();
        assertThat(userextraList).hasSize(databaseSizeBeforeUpdate);
        Userextra testUserextra = userextraList.get(userextraList.size() - 1);
        assertThat(testUserextra.getMiddlename()).isEqualTo(UPDATED_MIDDLENAME);
        assertThat(testUserextra.getJobrole()).isEqualTo(UPDATED_JOBROLE);
        assertThat(testUserextra.getDob()).isEqualTo(UPDATED_DOB);
    }

    @Test
    @Transactional
    void partialUpdateUserextraShouldThrown() throws Exception {
        // Update the userextra without id should throw
        Userextra partialUpdatedUserextra = new Userextra();

        restUserextraMockMvc
            .perform(
                patch("/api/userextras")
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserextra))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteUserextra() throws Exception {
        // Initialize the database
        userextraRepository.saveAndFlush(userextra);

        int databaseSizeBeforeDelete = userextraRepository.findAll().size();

        // Delete the userextra
        restUserextraMockMvc
            .perform(delete("/api/userextras/{id}", userextra.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Userextra> userextraList = userextraRepository.findAll();
        assertThat(userextraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
