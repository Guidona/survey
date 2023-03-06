package io.survey.service;

import io.survey.service.dto.QuestionOuverteDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.survey.model.QuestionOuverte}.
 */
public interface QuestionOuverteService {
    /**
     * Save a questionOuverte.
     *
     * @param questionOuverteDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionOuverteDTO save(QuestionOuverteDTO questionOuverteDTO);

    /**
     * Updates a questionOuverte.
     *
     * @param questionOuverteDTO the entity to update.
     * @return the persisted entity.
     */
    QuestionOuverteDTO update(QuestionOuverteDTO questionOuverteDTO);

    /**
     * Partially updates a questionOuverte.
     *
     * @param questionOuverteDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuestionOuverteDTO> partialUpdate(QuestionOuverteDTO questionOuverteDTO);

    /**
     * Get all the questionOuvertes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuestionOuverteDTO> findAll(Pageable pageable);

    /**
     * Get the "id" questionOuverte.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionOuverteDTO> findOne(Long id);

    /**
     * Delete the "id" questionOuverte.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
