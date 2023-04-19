package io.survey.service;

import io.survey.service.dto.QuestionnaireDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.survey.model.Questionnaire}.
 */
public interface QuestionnaireService {
    /**
     * Save a questionnaire.
     *
     * @param questionnaireDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionnaireDTO save(QuestionnaireDTO questionnaireDTO);

    /**
     * Updates a questionnaire.
     *
     * @param questionnaireDTO the entity to update.
     * @return the persisted entity.
     */
    QuestionnaireDTO update(QuestionnaireDTO questionnaireDTO);

    /**
     * Partially updates a questionnaire.
     *
     * @param questionnaireDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuestionnaireDTO> partialUpdate(QuestionnaireDTO questionnaireDTO);

    /**
     * Get all the questionnaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuestionnaireDTO> findAll(Pageable pageable);

    List<QuestionnaireDTO> findAll();

    /**
     * Get the "id" questionnaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionnaireDTO> findOne(Long id);

    /**
     * Delete the "id" questionnaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
