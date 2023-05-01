package com.targetis.gradrole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.targetis.gradrole.IntegrationTest;
import com.targetis.gradrole.domain.User;
import com.targetis.gradrole.domain.UserExt;
import com.targetis.gradrole.repository.UserExtRepository;
import com.targetis.gradrole.repository.UserRepository;
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
 * Integration tests for the {@link UserExtResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserExtResourceIT {

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_ROLE = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_BIRTH = "BBBBBBBBBB";

    @Autowired
    private UserExtRepository userExtRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserExtMockMvc;

    private UserExt userExt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExt createEntity(EntityManager em) {
        UserExt userExt = new UserExt().middleName(DEFAULT_MIDDLE_NAME).jobRole(DEFAULT_JOB_ROLE).dateOfBirth(DEFAULT_DATE_OF_BIRTH);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        userExt.setUser(user);
        return userExt;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserExt createUpdatedEntity(EntityManager em) {
        UserExt userExt = new UserExt().middleName(UPDATED_MIDDLE_NAME).jobRole(UPDATED_JOB_ROLE).dateOfBirth(UPDATED_DATE_OF_BIRTH);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        userExt.setUser(user);
        return userExt;
    }

    @BeforeEach
    public void initTest() {
        userExt = createEntity(em);
    }

    @Test
    @Transactional
    void createUserExt() throws Exception {
        int databaseSizeBeforeCreate = userExtRepository.findAll().size();
        // Create the UserExt
        restUserExtMockMvc
            .perform(
                post("/api/user-exts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userExt))
            )
            .andExpect(status().isCreated());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeCreate + 1);
        UserExt testUserExt = userExtList.get(userExtList.size() - 1);
        assertThat(testUserExt.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testUserExt.getJobRole()).isEqualTo(DEFAULT_JOB_ROLE);
        assertThat(testUserExt.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);

        // Validate the id for MapsId, the ids must be same
        assertThat(testUserExt.getId()).isEqualTo(testUserExt.getUser().getId());
    }

    @Test
    @Transactional
    void createUserExtWithExistingId() throws Exception {
        // Create the UserExt with an existing ID
        userExt.setId(1L);

        int databaseSizeBeforeCreate = userExtRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserExtMockMvc
            .perform(
                post("/api/user-exts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userExt))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void updateUserExtMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);
        int databaseSizeBeforeCreate = userExtRepository.findAll().size();

        // Add a new parent entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();

        // Load the userExt
        UserExt updatedUserExt = userExtRepository.findById(userExt.getId()).get();
        assertThat(updatedUserExt).isNotNull();
        // Disconnect from session so that the updates on updatedUserExt are not directly saved in db
        em.detach(updatedUserExt);

        // Update the User with new association value
        updatedUserExt.setUser(user);

        // Update the entity
        restUserExtMockMvc
            .perform(
                put("/api/user-exts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUserExt))
            )
            .andExpect(status().isOk());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeCreate);
        UserExt testUserExt = userExtList.get(userExtList.size() - 1);
        // Validate the id for MapsId, the ids must be same
        // Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
        // Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
        // assertThat(testUserExt.getId()).isEqualTo(testUserExt.getUser().getId());
    }

    @Test
    @Transactional
    void getAllUserExts() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);

        // Get all the userExtList
        restUserExtMockMvc
            .perform(get("/api/user-exts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userExt.getId().intValue())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].jobRole").value(hasItem(DEFAULT_JOB_ROLE)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH)));
    }

    @Test
    @Transactional
    void getUserExt() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);

        // Get the userExt
        restUserExtMockMvc
            .perform(get("/api/user-exts/{id}", userExt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userExt.getId().intValue()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.jobRole").value(DEFAULT_JOB_ROLE))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH));
    }

    @Test
    @Transactional
    void getNonExistingUserExt() throws Exception {
        // Get the userExt
        restUserExtMockMvc.perform(get("/api/user-exts/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateUserExt() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);

        int databaseSizeBeforeUpdate = userExtRepository.findAll().size();

        // Update the userExt
        UserExt updatedUserExt = userExtRepository.findById(userExt.getId()).get();
        // Disconnect from session so that the updates on updatedUserExt are not directly saved in db
        em.detach(updatedUserExt);
        updatedUserExt.middleName(UPDATED_MIDDLE_NAME).jobRole(UPDATED_JOB_ROLE).dateOfBirth(UPDATED_DATE_OF_BIRTH);

        restUserExtMockMvc
            .perform(
                put("/api/user-exts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUserExt))
            )
            .andExpect(status().isOk());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeUpdate);
        UserExt testUserExt = userExtList.get(userExtList.size() - 1);
        assertThat(testUserExt.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testUserExt.getJobRole()).isEqualTo(UPDATED_JOB_ROLE);
        assertThat(testUserExt.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void updateNonExistingUserExt() throws Exception {
        int databaseSizeBeforeUpdate = userExtRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserExtMockMvc
            .perform(
                put("/api/user-exts")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userExt))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserExtWithPatch() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);

        int databaseSizeBeforeUpdate = userExtRepository.findAll().size();

        // Update the userExt using partial update
        UserExt partialUpdatedUserExt = new UserExt();
        partialUpdatedUserExt.setId(userExt.getId());

        partialUpdatedUserExt.jobRole(UPDATED_JOB_ROLE);

        restUserExtMockMvc
            .perform(
                patch("/api/user-exts")
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserExt))
            )
            .andExpect(status().isOk());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeUpdate);
        UserExt testUserExt = userExtList.get(userExtList.size() - 1);
        assertThat(testUserExt.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testUserExt.getJobRole()).isEqualTo(UPDATED_JOB_ROLE);
        assertThat(testUserExt.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void fullUpdateUserExtWithPatch() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);

        int databaseSizeBeforeUpdate = userExtRepository.findAll().size();

        // Update the userExt using partial update
        UserExt partialUpdatedUserExt = new UserExt();
        partialUpdatedUserExt.setId(userExt.getId());

        partialUpdatedUserExt.middleName(UPDATED_MIDDLE_NAME).jobRole(UPDATED_JOB_ROLE).dateOfBirth(UPDATED_DATE_OF_BIRTH);

        restUserExtMockMvc
            .perform(
                patch("/api/user-exts")
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserExt))
            )
            .andExpect(status().isOk());

        // Validate the UserExt in the database
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeUpdate);
        UserExt testUserExt = userExtList.get(userExtList.size() - 1);
        assertThat(testUserExt.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testUserExt.getJobRole()).isEqualTo(UPDATED_JOB_ROLE);
        assertThat(testUserExt.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void partialUpdateUserExtShouldThrown() throws Exception {
        // Update the userExt without id should throw
        UserExt partialUpdatedUserExt = new UserExt();

        restUserExtMockMvc
            .perform(
                patch("/api/user-exts")
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserExt))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteUserExt() throws Exception {
        // Initialize the database
        userExtRepository.saveAndFlush(userExt);

        int databaseSizeBeforeDelete = userExtRepository.findAll().size();

        // Delete the userExt
        restUserExtMockMvc
            .perform(delete("/api/user-exts/{id}", userExt.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserExt> userExtList = userExtRepository.findAll();
        assertThat(userExtList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
