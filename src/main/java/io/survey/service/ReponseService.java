package io.survey.service;

import io.survey.service.dto.ReponseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.survey.model.Reponse}.
 */
public interface ReponseService {
    /**
     * Save a reponse.
     *
     * @param reponseDTO the entity to save.
     * @return the persisted entity.
     */
    ReponseDTO save(ReponseDTO reponseDTO);

    /**
     * Updates a reponse.
     *
     * @param reponseDTO the entity to update.
     * @return the persisted entity.
     */
    ReponseDTO update(ReponseDTO reponseDTO);

    /**
     * Partially updates a reponse.
     *
     * @param reponseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReponseDTO> partialUpdate(ReponseDTO reponseDTO);

    /**
     * Get all the reponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReponseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" reponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReponseDTO> findOne(Long id);

    /**
     * Delete the "id" reponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
