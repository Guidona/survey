package io.survey.web.rest;

import io.survey.repository.ReponseRepository;
import io.survey.service.ReponseService;
import io.survey.service.dto.ReponseDTO;
import io.survey.web.rest.errors.BadRequestAlertException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link io.survey.model.Reponse}.
 */
@RestController
@RequestMapping("/api")
public class ReponseResource {

    private final Logger log = LoggerFactory.getLogger(ReponseResource.class);

    private static final String ENTITY_NAME = "reponse";


    private final ReponseService reponseService;

    private final ReponseRepository reponseRepository;

    public ReponseResource(ReponseService reponseService, ReponseRepository reponseRepository) {
        this.reponseService = reponseService;
        this.reponseRepository = reponseRepository;
    }

    /**
     * {@code POST  /reponses} : Create a new reponse.
     *
     * @param reponseDTO the reponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reponseDTO, or with status {@code 400 (Bad Request)} if the reponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reponses")
    public ResponseEntity<ReponseDTO> createReponse(@RequestBody ReponseDTO reponseDTO) throws URISyntaxException {
        log.debug("REST request to save Reponse : {}", reponseDTO);
        if (reponseDTO.getId() != null) {
            throw new BadRequestAlertException("A new reponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReponseDTO result = reponseService.save(reponseDTO);
        return ResponseEntity
            .created(new URI("/api/reponses/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /reponses/:id} : Updates an existing reponse.
     *
     * @param id the id of the reponseDTO to save.
     * @param reponseDTO the reponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reponseDTO,
     * or with status {@code 400 (Bad Request)} if the reponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reponses/{id}")
    public ResponseEntity<ReponseDTO> updateReponse(@PathVariable(value = "id", required = false) final Long id,
                                                    @RequestBody ReponseDTO reponseDTO) throws URISyntaxException {
        log.debug("REST request to update Reponse : {}, {}", id, reponseDTO);
        if (reponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reponseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReponseDTO result = reponseService.update(reponseDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /reponses/:id} : Partial updates given fields of an existing reponse, field will ignore if it is null
     *
     * @param id the id of the reponseDTO to save.
     * @param reponseDTO the reponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reponseDTO,
     * or with status {@code 400 (Bad Request)} if the reponseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the reponseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the reponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reponses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReponseDTO> partialUpdateReponse(@PathVariable(value = "id", required = false) final Long id,
                                                           @RequestBody ReponseDTO reponseDTO) throws URISyntaxException {
        log.debug("REST request to partial update Reponse partially : {}, {}", id, reponseDTO);
        if (reponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reponseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReponseDTO> result = reponseService.partialUpdate(reponseDTO);

        return ResponseEntity.of(result);

    }

    /**
     * {@code GET  /reponses} : get all the reponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reponses in body.
     */
    @GetMapping("/reponses")
    public ResponseEntity<List<ReponseDTO>> getAllReponses(Pageable pageable) {
        log.debug("REST request to get a page of Reponses");
        Page<ReponseDTO> page = reponseService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /reponses/:id} : get the "id" reponse.
     *
     * @param id the id of the reponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reponses/{id}")
    public ResponseEntity<ReponseDTO> getReponse(@PathVariable Long id) {
        log.debug("REST request to get Reponse : {}", id);
        Optional<ReponseDTO> reponseDTO = reponseService.findOne(id);
        return ResponseEntity.of(reponseDTO);
    }

    /**
     * {@code DELETE  /reponses/:id} : delete the "id" reponse.
     *
     * @param id the id of the reponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reponses/{id}")
    public ResponseEntity<Void> deleteReponse(@PathVariable Long id) {
        log.debug("REST request to delete Reponse : {}", id);
        reponseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
