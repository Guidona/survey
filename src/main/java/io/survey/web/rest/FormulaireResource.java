package io.survey.web.rest;

import io.survey.repository.FormulaireRepository;
import io.survey.service.FormulaireService;
import io.survey.service.dto.FormulaireDTO;
import io.survey.web.rest.errors.BadRequestAlertException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link io.survey.model.Formulaire}.
 */
@RestController
@RequestMapping("/api")
public class FormulaireResource {

    private final Logger log = LoggerFactory.getLogger(FormulaireResource.class);

    private static final String ENTITY_NAME = "formulaire";

    private final FormulaireService formulaireService;

    private final FormulaireRepository formulaireRepository;

    public FormulaireResource(FormulaireService formulaireService, FormulaireRepository formulaireRepository) {
        this.formulaireService = formulaireService;
        this.formulaireRepository = formulaireRepository;
    }

    /**
     * {@code POST  /formulaires} : Create a new formulaire.
     *
     * @param formulaireDTO the formulaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formulaireDTO, or with status {@code 400 (Bad Request)} if the formulaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formulaires")
    @Operation(summary = "Operation pour creer un nouveau formulaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(schema = @Schema(implementation = FormulaireDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<FormulaireDTO> createFormulaire(@RequestBody FormulaireDTO formulaireDTO) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save Formulaire : {}", formulaireDTO);
        if (formulaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new formulaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormulaireDTO result = formulaireService.save(formulaireDTO);
        return ResponseEntity
                .created(new URI("/api/formulaires/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /formulaires/:id} : Updates an existing formulaire.
     *
     * @param id            the id of the formulaireDTO to save.
     * @param formulaireDTO the formulaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formulaireDTO,
     * or with status {@code 400 (Bad Request)} if the formulaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formulaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formulaires/{id}")
    public ResponseEntity<FormulaireDTO> updateFormulaire(@PathVariable(value = "id", required = false) final Long id,
                                                          @RequestBody FormulaireDTO formulaireDTO) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update Formulaire : {}, {}", id, formulaireDTO);
        if (formulaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formulaireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formulaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FormulaireDTO result = formulaireService.update(formulaireDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code PATCH  /formulaires/:id} : Partial updates given fields of an existing formulaire, field will ignore if it is null
     *
     * @param id            the id of the formulaireDTO to save.
     * @param formulaireDTO the formulaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formulaireDTO,
     * or with status {@code 400 (Bad Request)} if the formulaireDTO is not valid,
     * or with status {@code 404 (Not Found)} if the formulaireDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the formulaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/formulaires/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<FormulaireDTO> partialUpdateFormulaire(@PathVariable(value = "id", required = false) final Long id,
                                                                 @RequestBody FormulaireDTO formulaireDTO) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to partial update Formulaire partially : {}, {}", id, formulaireDTO);
        if (formulaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formulaireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formulaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FormulaireDTO> result = formulaireService.partialUpdate(formulaireDTO);

        return ResponseEntity.ok().body(result.orElse(new FormulaireDTO()));

    }

    /**
     * {@code GET  /formulaires} : get all the formulaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formulaires in body.
     */
    @GetMapping("/formulaires")
    public ResponseEntity<List<FormulaireDTO>> getAllFormulaires(@Param("questionnaireId") Long questionnaireId,
                                                                 Pageable pageable) {
        log.debug("REST request to get a page of Formulaires");
        Page<FormulaireDTO> page = formulaireService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /formulaires/:id} : get the "id" formulaire.
     *
     * @param id the id of the formulaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formulaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formulaires/{id}")
    public ResponseEntity<FormulaireDTO> getFormulaire(@PathVariable Long id) {
        log.debug("REST request to get Formulaire : {}", id);
        Optional<FormulaireDTO> formulaireDTO = formulaireService.findOne(id);
        return ResponseEntity.ok(formulaireDTO.orElse(new FormulaireDTO()));
    }

    /**
     * {@code DELETE  /formulaires/:id} : delete the "id" formulaire.
     *
     * @param id the id of the formulaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formulaires/{id}")
    public ResponseEntity<Void> deleteFormulaire(@PathVariable Long id) {
        log.debug("REST request to delete Formulaire : {}", id);
        formulaireService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
