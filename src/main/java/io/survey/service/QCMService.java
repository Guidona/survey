package io.survey.service;

import io.survey.service.dto.QCMDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.survey.model.QCM}.
 */
public interface QCMService {
    /**
     * Save a qCM.
     *
     * @param qCMDTO the entity to save.
     * @return the persisted entity.
     */
    QCMDTO save(QCMDTO qCMDTO);

    /**
     * Updates a qCM.
     *
     * @param qCMDTO the entity to update.
     * @return the persisted entity.
     */
    QCMDTO update(QCMDTO qCMDTO);

    /**
     * Partially updates a qCM.
     *
     * @param qCMDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QCMDTO> partialUpdate(QCMDTO qCMDTO);

    /**
     * Get all the qCMS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QCMDTO> findAll(Pageable pageable);

    /**
     * Get the "id" qCM.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QCMDTO> findOne(Long id);

    /**
     * Delete the "id" qCM.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
