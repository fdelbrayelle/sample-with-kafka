package tech.ippon.generated.web.rest;

import tech.ippon.generated.GeneratedProjectForTestsApp;
import tech.ippon.generated.domain.UpperCamelClazz;
import tech.ippon.generated.repository.UpperCamelClazzRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UpperCamelClazzResource} REST controller.
 */
@SpringBootTest(classes = GeneratedProjectForTestsApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UpperCamelClazzResourceIT {

    @Autowired
    private UpperCamelClazzRepository upperCamelClazzRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUpperCamelClazzMockMvc;

    private UpperCamelClazz upperCamelClazz;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UpperCamelClazz createEntity(EntityManager em) {
        UpperCamelClazz upperCamelClazz = new UpperCamelClazz();
        return upperCamelClazz;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UpperCamelClazz createUpdatedEntity(EntityManager em) {
        UpperCamelClazz upperCamelClazz = new UpperCamelClazz();
        return upperCamelClazz;
    }

    @BeforeEach
    public void initTest() {
        upperCamelClazz = createEntity(em);
    }

    @Test
    @Transactional
    public void createUpperCamelClazz() throws Exception {
        int databaseSizeBeforeCreate = upperCamelClazzRepository.findAll().size();

        // Create the UpperCamelClazz
        restUpperCamelClazzMockMvc.perform(post("/api/upper-camel-clazzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(upperCamelClazz)))
            .andExpect(status().isCreated());

        // Validate the UpperCamelClazz in the database
        List<UpperCamelClazz> upperCamelClazzList = upperCamelClazzRepository.findAll();
        assertThat(upperCamelClazzList).hasSize(databaseSizeBeforeCreate + 1);
        UpperCamelClazz testUpperCamelClazz = upperCamelClazzList.get(upperCamelClazzList.size() - 1);
    }

    @Test
    @Transactional
    public void createUpperCamelClazzWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = upperCamelClazzRepository.findAll().size();

        // Create the UpperCamelClazz with an existing ID
        upperCamelClazz.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUpperCamelClazzMockMvc.perform(post("/api/upper-camel-clazzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(upperCamelClazz)))
            .andExpect(status().isBadRequest());

        // Validate the UpperCamelClazz in the database
        List<UpperCamelClazz> upperCamelClazzList = upperCamelClazzRepository.findAll();
        assertThat(upperCamelClazzList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUpperCamelClazzes() throws Exception {
        // Initialize the database
        upperCamelClazzRepository.saveAndFlush(upperCamelClazz);

        // Get all the upperCamelClazzList
        restUpperCamelClazzMockMvc.perform(get("/api/upper-camel-clazzes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(upperCamelClazz.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUpperCamelClazz() throws Exception {
        // Initialize the database
        upperCamelClazzRepository.saveAndFlush(upperCamelClazz);

        // Get the upperCamelClazz
        restUpperCamelClazzMockMvc.perform(get("/api/upper-camel-clazzes/{id}", upperCamelClazz.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(upperCamelClazz.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUpperCamelClazz() throws Exception {
        // Get the upperCamelClazz
        restUpperCamelClazzMockMvc.perform(get("/api/upper-camel-clazzes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUpperCamelClazz() throws Exception {
        // Initialize the database
        upperCamelClazzRepository.saveAndFlush(upperCamelClazz);

        int databaseSizeBeforeUpdate = upperCamelClazzRepository.findAll().size();

        // Update the upperCamelClazz
        UpperCamelClazz updatedUpperCamelClazz = upperCamelClazzRepository.findById(upperCamelClazz.getId()).get();
        // Disconnect from session so that the updates on updatedUpperCamelClazz are not directly saved in db
        em.detach(updatedUpperCamelClazz);

        restUpperCamelClazzMockMvc.perform(put("/api/upper-camel-clazzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUpperCamelClazz)))
            .andExpect(status().isOk());

        // Validate the UpperCamelClazz in the database
        List<UpperCamelClazz> upperCamelClazzList = upperCamelClazzRepository.findAll();
        assertThat(upperCamelClazzList).hasSize(databaseSizeBeforeUpdate);
        UpperCamelClazz testUpperCamelClazz = upperCamelClazzList.get(upperCamelClazzList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingUpperCamelClazz() throws Exception {
        int databaseSizeBeforeUpdate = upperCamelClazzRepository.findAll().size();

        // Create the UpperCamelClazz

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUpperCamelClazzMockMvc.perform(put("/api/upper-camel-clazzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(upperCamelClazz)))
            .andExpect(status().isBadRequest());

        // Validate the UpperCamelClazz in the database
        List<UpperCamelClazz> upperCamelClazzList = upperCamelClazzRepository.findAll();
        assertThat(upperCamelClazzList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUpperCamelClazz() throws Exception {
        // Initialize the database
        upperCamelClazzRepository.saveAndFlush(upperCamelClazz);

        int databaseSizeBeforeDelete = upperCamelClazzRepository.findAll().size();

        // Delete the upperCamelClazz
        restUpperCamelClazzMockMvc.perform(delete("/api/upper-camel-clazzes/{id}", upperCamelClazz.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UpperCamelClazz> upperCamelClazzList = upperCamelClazzRepository.findAll();
        assertThat(upperCamelClazzList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
