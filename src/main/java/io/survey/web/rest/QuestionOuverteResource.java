package io.survey.web.rest;

import io.survey.repository.QuestionOuverteRepository;
import io.survey.service.QuestionOuverteService;
import io.survey.service.dto.QuestionOuverteDTO;
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
 * REST controller for managing {@link io.survey.model.QuestionOuverte}.
 */
@RestController
@RequestMapping("/api")
public class QuestionOuverteResource {

    private final Logger log = LoggerFactory.getLogger(QuestionOuverteResource.class);

    private static final String ENTITY_NAME = "questionOuverte";

    private final QuestionOuverteService questionOuverteService;

    private final QuestionOuverteRepository questionOuverteRepository;

    public QuestionOuverteResource(QuestionOuverteService questionOuverteService, QuestionOuverteRepository questionOuverteRepository) {
        this.questionOuverteService = questionOuverteService;
        this.questionOuverteRepository = questionOuverteRepository;
    }

    /**
     * {@code POST  /question-ouvertes} : Create a new questionOuverte.
     *
     * @param questionOuverteDTO the questionOuverteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionOuverteDTO, or with status {@code 400 (Bad Request)} if the questionOuverte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/question-ouvertes")
    public ResponseEntity<QuestionOuverteDTO> createQuestionOuverte(@RequestBody QuestionOuverteDTO questionOuverteDTO)
        throws URISyntaxException {
        log.debug("REST request to save QuestionOuverte : {}", questionOuverteDTO);
        if (questionOuverteDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionOuverte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionOuverteDTO result = questionOuverteService.save(questionOuverteDTO);
        return ResponseEntity
            .created(new URI("/api/question-ouvertes/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /question-ouvertes/:id} : Updates an existing questionOuverte.
     *
     * @param id the id of the questionOuverteDTO to save.
     * @param questionOuverteDTO the questionOuverteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionOuverteDTO,
     * or with status {@code 400 (Bad Request)} if the questionOuverteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionOuverteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/question-ouvertes/{id}")
    public ResponseEntity<QuestionOuverteDTO> updateQuestionOuverte(@PathVariable(value = "id", required = false) final Long id,
                                                                    @RequestBody QuestionOuverteDTO questionOuverteDTO) throws URISyntaxException {
        log.debug("REST request to update QuestionOuverte : {}, {}", id, questionOuverteDTO);
        if (questionOuverteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionOuverteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionOuverteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QuestionOuverteDTO result = questionOuverteService.update(questionOuverteDTO);

        return ResponseEntity.ok().body(result);

    }

    /**
     * {@code PATCH  /question-ouvertes/:id} : Partial updates given fields of an existing questionOuverte, field will ignore if it is null
     *
     * @param id the id of the questionOuverteDTO to save.
     * @param questionOuverteDTO the questionOuverteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionOuverteDTO,
     * or with status {@code 400 (Bad Request)} if the questionOuverteDTO is not valid,
     * or with status {@code 404 (Not Found)} if the questionOuverteDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the questionOuverteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/question-ouvertes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuestionOuverteDTO> partialUpdateQuestionOuverte(@PathVariable(value = "id", required = false) final Long id,
                                                                           @RequestBody QuestionOuverteDTO questionOuverteDTO) throws URISyntaxException {

        log.debug("REST request to partial update QuestionOuverte partially : {}, {}", id, questionOuverteDTO);
        if (questionOuverteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionOuverteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionOuverteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuestionOuverteDTO> result = questionOuverteService.partialUpdate(questionOuverteDTO);

        return ResponseEntity.of(result);

    }

    /**
     * {@code GET  /question-ouvertes} : get all the questionOuvertes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionOuvertes in body.
     */
    @GetMapping("/question-ouvertes")
    public ResponseEntity<List<QuestionOuverteDTO>> getAllQuestionOuvertes(Pageable pageable) {
        log.debug("REST request to get a page of QuestionOuvertes");
        Page<QuestionOuverteDTO> page = questionOuverteService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /question-ouvertes/:id} : get the "id" questionOuverte.
     *
     * @param id the id of the questionOuverteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionOuverteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-ouvertes/{id}")
    public ResponseEntity<QuestionOuverteDTO> getQuestionOuverte(@PathVariable Long id) {
        log.debug("REST request to get QuestionOuverte : {}", id);
        Optional<QuestionOuverteDTO> questionOuverteDTO = questionOuverteService.findOne(id);
        return ResponseEntity.of(questionOuverteDTO);
    }

    /**
     * {@code DELETE  /question-ouvertes/:id} : delete the "id" questionOuverte.
     *
     * @param id the id of the questionOuverteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/question-ouvertes/{id}")
    public ResponseEntity<Void> deleteQuestionOuverte(@PathVariable Long id) {
        log.debug("REST request to delete QuestionOuverte : {}", id);
        questionOuverteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
