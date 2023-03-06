package io.survey.service.impl;

import io.survey.model.Section;
import io.survey.repository.SectionRepository;
import io.survey.service.SectionService;
import io.survey.service.dto.SectionDTO;
import io.survey.service.mapper.SectionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Section}.
 */
@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    private final Logger log = LoggerFactory.getLogger(SectionServiceImpl.class);

    private final SectionRepository sectionRepository;

    private final SectionMapper sectionMapper;

    public SectionServiceImpl(SectionRepository sectionRepository, SectionMapper sectionMapper) {
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
    }

    @Override
    public SectionDTO save(SectionDTO sectionDTO) {
        log.debug("Request to save Section : {}", sectionDTO);
        Section section = sectionMapper.toEntity(sectionDTO);
        section = sectionRepository.save(section);
        return sectionMapper.toDto(section);
    }

    @Override
    public SectionDTO update(SectionDTO sectionDTO) {
        log.debug("Request to update Section : {}", sectionDTO);
        Section section = sectionMapper.toEntity(sectionDTO);
        section = sectionRepository.save(section);
        return sectionMapper.toDto(section);
    }

    @Override
    public Optional<SectionDTO> partialUpdate(SectionDTO sectionDTO) {
        log.debug("Request to partially update Section : {}", sectionDTO);

        return sectionRepository
            .findById(sectionDTO.getId())
            .map(existingSection -> {
                sectionMapper.partialUpdate(existingSection, sectionDTO);

                return existingSection;
            })
            .map(sectionRepository::save)
            .map(sectionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SectionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sections");
        return sectionRepository.findAll(pageable).map(sectionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SectionDTO> findOne(Long id) {
        log.debug("Request to get Section : {}", id);
        return sectionRepository.findById(id).map(sectionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Section : {}", id);
        sectionRepository.deleteById(id);
    }
}
