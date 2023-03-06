package io.survey.service;

import io.survey.service.dto.SectionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.survey.model.Section}.
 */
public interface SectionService {
    /**
     * Save a section.
     *
     * @param sectionDTO the entity to save.
     * @return the persisted entity.
     */
    SectionDTO save(SectionDTO sectionDTO);

    /**
     * Updates a section.
     *
     * @param sectionDTO the entity to update.
     * @return the persisted entity.
     */
    SectionDTO update(SectionDTO sectionDTO);

    /**
     * Partially updates a section.
     *
     * @param sectionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SectionDTO> partialUpdate(SectionDTO sectionDTO);

    /**
     * Get all the sections.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SectionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" section.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SectionDTO> findOne(Long id);

    /**
     * Delete the "id" section.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
