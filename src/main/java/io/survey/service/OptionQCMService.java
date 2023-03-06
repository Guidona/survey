package io.survey.service;

import io.survey.service.dto.OptionQCMDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.survey.model.OptionQCM}.
 */
public interface OptionQCMService {
    /**
     * Save a optionQCM.
     *
     * @param optionQCMDTO the entity to save.
     * @return the persisted entity.
     */
    OptionQCMDTO save(OptionQCMDTO optionQCMDTO);

    /**
     * Updates a optionQCM.
     *
     * @param optionQCMDTO the entity to update.
     * @return the persisted entity.
     */
    OptionQCMDTO update(OptionQCMDTO optionQCMDTO);

    /**
     * Partially updates a optionQCM.
     *
     * @param optionQCMDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OptionQCMDTO> partialUpdate(OptionQCMDTO optionQCMDTO);

    /**
     * Get all the optionQCMS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OptionQCMDTO> findAll(Pageable pageable);

    /**
     * Get the "id" optionQCM.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OptionQCMDTO> findOne(Long id);

    /**
     * Delete the "id" optionQCM.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
