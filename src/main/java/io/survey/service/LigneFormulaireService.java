package io.survey.service;

import io.survey.service.dto.LigneFormulaireDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.survey.model.LigneFormulaire}.
 */
public interface LigneFormulaireService {
    /**
     * Save a ligneFormulaire.
     *
     * @param ligneFormulaireDTO the entity to save.
     * @return the persisted entity.
     */
    LigneFormulaireDTO save(LigneFormulaireDTO ligneFormulaireDTO);

    /**
     * Updates a ligneFormulaire.
     *
     * @param ligneFormulaireDTO the entity to update.
     * @return the persisted entity.
     */
    LigneFormulaireDTO update(LigneFormulaireDTO ligneFormulaireDTO);

    /**
     * Partially updates a ligneFormulaire.
     *
     * @param ligneFormulaireDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LigneFormulaireDTO> partialUpdate(LigneFormulaireDTO ligneFormulaireDTO);

    /**
     * Get all the ligneFormulaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LigneFormulaireDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ligneFormulaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LigneFormulaireDTO> findOne(Long id);

    /**
     * Delete the "id" ligneFormulaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
