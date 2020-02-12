package tech.ippon.generated.web.rest;

import tech.ippon.generated.service.FooService;
import tech.ippon.generated.web.rest.errors.BadRequestAlertException;
import tech.ippon.generated.service.dto.FooDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link tech.ippon.generated.domain.Foo}.
 */
@RestController
@RequestMapping("/api")
public class FooResource {

    private final Logger log = LoggerFactory.getLogger(FooResource.class);

    private static final String ENTITY_NAME = "foo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FooService fooService;

    public FooResource(FooService fooService) {
        this.fooService = fooService;
    }

    /**
     * {@code POST  /foos} : Create a new foo.
     *
     * @param fooDTO the fooDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fooDTO, or with status {@code 400 (Bad Request)} if the foo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/foos")
    public ResponseEntity<FooDTO> createFoo(@RequestBody FooDTO fooDTO) throws URISyntaxException {
        log.debug("REST request to save Foo : {}", fooDTO);
        if (fooDTO.getId() != null) {
            throw new BadRequestAlertException("A new foo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FooDTO result = fooService.save(fooDTO);
        return ResponseEntity.created(new URI("/api/foos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /foos} : Updates an existing foo.
     *
     * @param fooDTO the fooDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fooDTO,
     * or with status {@code 400 (Bad Request)} if the fooDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fooDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/foos")
    public ResponseEntity<FooDTO> updateFoo(@RequestBody FooDTO fooDTO) throws URISyntaxException {
        log.debug("REST request to update Foo : {}", fooDTO);
        if (fooDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FooDTO result = fooService.save(fooDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fooDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /foos} : get all the foos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foos in body.
     */
    @GetMapping("/foos")
    public List<FooDTO> getAllFoos() {
        log.debug("REST request to get all Foos");
        return fooService.findAll();
    }

    /**
     * {@code GET  /foos/:id} : get the "id" foo.
     *
     * @param id the id of the fooDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fooDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/foos/{id}")
    public ResponseEntity<FooDTO> getFoo(@PathVariable Long id) {
        log.debug("REST request to get Foo : {}", id);
        Optional<FooDTO> fooDTO = fooService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fooDTO);
    }

    /**
     * {@code DELETE  /foos/:id} : delete the "id" foo.
     *
     * @param id the id of the fooDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/foos/{id}")
    public ResponseEntity<Void> deleteFoo(@PathVariable Long id) {
        log.debug("REST request to delete Foo : {}", id);
        fooService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
