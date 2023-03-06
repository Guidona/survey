package io.survey.web.rest;

import io.survey.repository.QuestionnaireRepository;
import io.survey.service.QuestionnaireService;
import io.survey.service.dto.QuestionnaireDTO;
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
 * REST controller for managing {@link io.survey.model.Questionnaire}.
 */
@RestController
@RequestMapping("/api")
public class QuestionnaireResource {

    private final Logger log = LoggerFactory.getLogger(QuestionnaireResource.class);

    private static final String ENTITY_NAME = "questionnaire";

    private final QuestionnaireService questionnaireService;

    private final QuestionnaireRepository questionnaireRepository;

    public QuestionnaireResource(QuestionnaireService questionnaireService, QuestionnaireRepository questionnaireRepository) {
        this.questionnaireService = questionnaireService;
        this.questionnaireRepository = questionnaireRepository;
    }

    /**
     * {@code POST  /questionnaires} : Create a new questionnaire.
     *
     * @param questionnaireDTO the questionnaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionnaireDTO, or with status {@code 400 (Bad Request)} if the questionnaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/questionnaires")
    public ResponseEntity<QuestionnaireDTO> createQuestionnaire(@RequestBody QuestionnaireDTO questionnaireDTO) throws URISyntaxException {
        log.debug("REST request to save Questionnaire : {}", questionnaireDTO);
        if (questionnaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionnaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionnaireDTO result = questionnaireService.save(questionnaireDTO);
        return ResponseEntity
            .created(new URI("/api/questionnaires/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /questionnaires/:id} : Updates an existing questionnaire.
     *
     * @param id the id of the questionnaireDTO to save.
     * @param questionnaireDTO the questionnaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionnaireDTO,
     * or with status {@code 400 (Bad Request)} if the questionnaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionnaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/questionnaires/{id}")
    public ResponseEntity<QuestionnaireDTO> updateQuestionnaire(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionnaireDTO questionnaireDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Questionnaire : {}, {}", id, questionnaireDTO);
        if (questionnaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionnaireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionnaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QuestionnaireDTO result = questionnaireService.update(questionnaireDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PATCH  /questionnaires/:id} : Partial updates given fields of an existing questionnaire, field will ignore if it is null
     *
     * @param id the id of the questionnaireDTO to save.
     * @param questionnaireDTO the questionnaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionnaireDTO,
     * or with status {@code 400 (Bad Request)} if the questionnaireDTO is not valid,
     * or with status {@code 404 (Not Found)} if the questionnaireDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the questionnaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/questionnaires/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuestionnaireDTO> partialUpdateQuestionnaire(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionnaireDTO questionnaireDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Questionnaire partially : {}, {}", id, questionnaireDTO);
        if (questionnaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionnaireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionnaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuestionnaireDTO> result = questionnaireService.partialUpdate(questionnaireDTO);

        return ResponseEntity.of(result);

    }

    /**
     * {@code GET  /questionnaires} : get all the questionnaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionnaires in body.
     */
    @GetMapping("/questionnaires")
    public ResponseEntity<List<QuestionnaireDTO>> getAllQuestionnaires(Pageable pageable) {
        log.debug("REST request to get a page of Questionnaires");
        Page<QuestionnaireDTO> page = questionnaireService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /questionnaires/:id} : get the "id" questionnaire.
     *
     * @param id the id of the questionnaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionnaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/questionnaires/{id}")
    public ResponseEntity<QuestionnaireDTO> getQuestionnaire(@PathVariable Long id) {
        log.debug("REST request to get Questionnaire : {}", id);
        Optional<QuestionnaireDTO> questionnaireDTO = questionnaireService.findOne(id);
        return ResponseEntity.of(questionnaireDTO);
    }

    /**
     * {@code DELETE  /questionnaires/:id} : delete the "id" questionnaire.
     *
     * @param id the id of the questionnaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/questionnaires/{id}")
    public ResponseEntity<Void> deleteQuestionnaire(@PathVariable Long id) {
        log.debug("REST request to delete Questionnaire : {}", id);
        questionnaireService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
