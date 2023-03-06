package io.survey.service;

import io.survey.service.dto.FormulaireDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.survey.model.Formulaire}.
 */
public interface FormulaireService {
    /**
     * Save a formulaire.
     *
     * @param formulaireDTO the entity to save.
     * @return the persisted entity.
     */
    FormulaireDTO save(FormulaireDTO formulaireDTO);

    /**
     * Updates a formulaire.
     *
     * @param formulaireDTO the entity to update.
     * @return the persisted entity.
     */
    FormulaireDTO update(FormulaireDTO formulaireDTO);

    /**
     * Partially updates a formulaire.
     *
     * @param formulaireDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FormulaireDTO> partialUpdate(FormulaireDTO formulaireDTO);

    /**
     * Get all the formulaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FormulaireDTO> findAll(Pageable pageable);

    /**
     * Get the "id" formulaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormulaireDTO> findOne(Long id);

    /**
     * Delete the "id" formulaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
