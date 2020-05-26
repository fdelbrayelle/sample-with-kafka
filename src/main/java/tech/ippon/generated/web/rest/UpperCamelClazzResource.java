package tech.ippon.generated.web.rest;

import tech.ippon.generated.domain.UpperCamelClazz;
import tech.ippon.generated.repository.UpperCamelClazzRepository;
import tech.ippon.generated.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link tech.ippon.generated.domain.UpperCamelClazz}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UpperCamelClazzResource {

    private final Logger log = LoggerFactory.getLogger(UpperCamelClazzResource.class);

    private static final String ENTITY_NAME = "upperCamelClazz";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UpperCamelClazzRepository upperCamelClazzRepository;

    public UpperCamelClazzResource(UpperCamelClazzRepository upperCamelClazzRepository) {
        this.upperCamelClazzRepository = upperCamelClazzRepository;
    }

    /**
     * {@code POST  /upper-camel-clazzes} : Create a new upperCamelClazz.
     *
     * @param upperCamelClazz the upperCamelClazz to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new upperCamelClazz, or with status {@code 400 (Bad Request)} if the upperCamelClazz has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/upper-camel-clazzes")
    public ResponseEntity<UpperCamelClazz> createUpperCamelClazz(@RequestBody UpperCamelClazz upperCamelClazz) throws URISyntaxException {
        log.debug("REST request to save UpperCamelClazz : {}", upperCamelClazz);
        if (upperCamelClazz.getId() != null) {
            throw new BadRequestAlertException("A new upperCamelClazz cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UpperCamelClazz result = upperCamelClazzRepository.save(upperCamelClazz);
        return ResponseEntity.created(new URI("/api/upper-camel-clazzes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /upper-camel-clazzes} : Updates an existing upperCamelClazz.
     *
     * @param upperCamelClazz the upperCamelClazz to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated upperCamelClazz,
     * or with status {@code 400 (Bad Request)} if the upperCamelClazz is not valid,
     * or with status {@code 500 (Internal Server Error)} if the upperCamelClazz couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/upper-camel-clazzes")
    public ResponseEntity<UpperCamelClazz> updateUpperCamelClazz(@RequestBody UpperCamelClazz upperCamelClazz) throws URISyntaxException {
        log.debug("REST request to update UpperCamelClazz : {}", upperCamelClazz);
        if (upperCamelClazz.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UpperCamelClazz result = upperCamelClazzRepository.save(upperCamelClazz);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, upperCamelClazz.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /upper-camel-clazzes} : get all the upperCamelClazzes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of upperCamelClazzes in body.
     */
    @GetMapping("/upper-camel-clazzes")
    public List<UpperCamelClazz> getAllUpperCamelClazzes() {
        log.debug("REST request to get all UpperCamelClazzes");
        return upperCamelClazzRepository.findAll();
    }

    /**
     * {@code GET  /upper-camel-clazzes/:id} : get the "id" upperCamelClazz.
     *
     * @param id the id of the upperCamelClazz to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the upperCamelClazz, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/upper-camel-clazzes/{id}")
    public ResponseEntity<UpperCamelClazz> getUpperCamelClazz(@PathVariable Long id) {
        log.debug("REST request to get UpperCamelClazz : {}", id);
        Optional<UpperCamelClazz> upperCamelClazz = upperCamelClazzRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(upperCamelClazz);
    }

    /**
     * {@code DELETE  /upper-camel-clazzes/:id} : delete the "id" upperCamelClazz.
     *
     * @param id the id of the upperCamelClazz to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/upper-camel-clazzes/{id}")
    public ResponseEntity<Void> deleteUpperCamelClazz(@PathVariable Long id) {
        log.debug("REST request to delete UpperCamelClazz : {}", id);
        upperCamelClazzRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
