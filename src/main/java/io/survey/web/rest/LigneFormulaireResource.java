package io.survey.web.rest;

import io.survey.repository.LigneFormulaireRepository;
import io.survey.service.LigneFormulaireService;
import io.survey.service.dto.LigneFormulaireDTO;
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
 * REST controller for managing {@link io.survey.model.LigneFormulaire}.
 */
@RestController
@RequestMapping("/api")
public class LigneFormulaireResource {

    private final Logger log = LoggerFactory.getLogger(LigneFormulaireResource.class);

    private static final String ENTITY_NAME = "ligneFormulaire";

    private final LigneFormulaireService ligneFormulaireService;

    private final LigneFormulaireRepository ligneFormulaireRepository;

    public LigneFormulaireResource(LigneFormulaireService ligneFormulaireService, LigneFormulaireRepository ligneFormulaireRepository) {
        this.ligneFormulaireService = ligneFormulaireService;
        this.ligneFormulaireRepository = ligneFormulaireRepository;
    }

    /**
     * {@code POST  /ligne-formulaires} : Create a new ligneFormulaire.
     *
     * @param ligneFormulaireDTO the ligneFormulaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ligneFormulaireDTO, or with status {@code 400 (Bad Request)} if the ligneFormulaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ligne-formulaires")
    public ResponseEntity<LigneFormulaireDTO> createLigneFormulaire(@RequestBody LigneFormulaireDTO ligneFormulaireDTO) throws URISyntaxException {
        log.debug("REST request to save LigneFormulaire : {}", ligneFormulaireDTO);
        if (ligneFormulaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new ligneFormulaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LigneFormulaireDTO result = ligneFormulaireService.save(ligneFormulaireDTO);
        return ResponseEntity
            .created(new URI("/api/ligne-formulaires/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ligne-formulaires/:id} : Updates an existing ligneFormulaire.
     *
     * @param id the id of the ligneFormulaireDTO to save.
     * @param ligneFormulaireDTO the ligneFormulaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ligneFormulaireDTO,
     * or with status {@code 400 (Bad Request)} if the ligneFormulaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ligneFormulaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ligne-formulaires/{id}")
    public ResponseEntity<LigneFormulaireDTO> updateLigneFormulaire(@PathVariable(value = "id", required = false) final Long id,
                                                                    @RequestBody LigneFormulaireDTO ligneFormulaireDTO) throws URISyntaxException {
        log.debug("REST request to update LigneFormulaire : {}, {}", id, ligneFormulaireDTO);
        if (ligneFormulaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ligneFormulaireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ligneFormulaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LigneFormulaireDTO result = ligneFormulaireService.update(ligneFormulaireDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /ligne-formulaires/:id} : Partial updates given fields of an existing ligneFormulaire, field will ignore if it is null
     *
     * @param id the id of the ligneFormulaireDTO to save.
     * @param ligneFormulaireDTO the ligneFormulaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ligneFormulaireDTO,
     * or with status {@code 400 (Bad Request)} if the ligneFormulaireDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ligneFormulaireDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ligneFormulaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ligne-formulaires/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LigneFormulaireDTO> partialUpdateLigneFormulaire(@PathVariable(value = "id", required = false) final Long id,
                                                                           @RequestBody LigneFormulaireDTO ligneFormulaireDTO) throws URISyntaxException {
        log.debug("REST request to partial update LigneFormulaire partially : {}, {}", id, ligneFormulaireDTO);
        if (ligneFormulaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ligneFormulaireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ligneFormulaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LigneFormulaireDTO> result = ligneFormulaireService.partialUpdate(ligneFormulaireDTO);

        return ResponseEntity.of(result);

    }

    /**
     * {@code GET  /ligne-formulaires} : get all the ligneFormulaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ligneFormulaires in body.
     */
    @GetMapping("/ligne-formulaires")
    public ResponseEntity<List<LigneFormulaireDTO>> getAllLigneFormulaires(Pageable pageable) {
        log.debug("REST request to get a page of LigneFormulaires");
        Page<LigneFormulaireDTO> page = ligneFormulaireService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /ligne-formulaires/:id} : get the "id" ligneFormulaire.
     *
     * @param id the id of the ligneFormulaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ligneFormulaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ligne-formulaires/{id}")
    public ResponseEntity<LigneFormulaireDTO> getLigneFormulaire(@PathVariable Long id) {
        log.debug("REST request to get LigneFormulaire : {}", id);
        Optional<LigneFormulaireDTO> ligneFormulaireDTO = ligneFormulaireService.findOne(id);
        return ResponseEntity.of(ligneFormulaireDTO);
    }

    /**
     * {@code DELETE  /ligne-formulaires/:id} : delete the "id" ligneFormulaire.
     *
     * @param id the id of the ligneFormulaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ligne-formulaires/{id}")
    public ResponseEntity<Void> deleteLigneFormulaire(@PathVariable Long id) {
        log.debug("REST request to delete LigneFormulaire : {}", id);
        ligneFormulaireService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
