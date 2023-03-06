package io.survey.service.impl;

import io.survey.model.OptionQCM;
import io.survey.repository.OptionQCMRepository;
import io.survey.service.OptionQCMService;
import io.survey.service.dto.OptionQCMDTO;
import io.survey.service.mapper.OptionQCMMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OptionQCM}.
 */
@Service
@Transactional
public class OptionQCMServiceImpl implements OptionQCMService {

    private final Logger log = LoggerFactory.getLogger(OptionQCMServiceImpl.class);

    private final OptionQCMRepository optionQCMRepository;

    private final OptionQCMMapper optionQCMMapper;

    public OptionQCMServiceImpl(OptionQCMRepository optionQCMRepository, OptionQCMMapper optionQCMMapper) {
        this.optionQCMRepository = optionQCMRepository;
        this.optionQCMMapper = optionQCMMapper;
    }

    @Override
    public OptionQCMDTO save(OptionQCMDTO optionQCMDTO) {
        log.debug("Request to save OptionQCM : {}", optionQCMDTO);
        OptionQCM optionQCM = optionQCMMapper.toEntity(optionQCMDTO);
        optionQCM = optionQCMRepository.save(optionQCM);
        return optionQCMMapper.toDto(optionQCM);
    }

    @Override
    public OptionQCMDTO update(OptionQCMDTO optionQCMDTO) {
        log.debug("Request to update OptionQCM : {}", optionQCMDTO);
        OptionQCM optionQCM = optionQCMMapper.toEntity(optionQCMDTO);
        optionQCM = optionQCMRepository.save(optionQCM);
        return optionQCMMapper.toDto(optionQCM);
    }

    @Override
    public Optional<OptionQCMDTO> partialUpdate(OptionQCMDTO optionQCMDTO) {
        log.debug("Request to partially update OptionQCM : {}", optionQCMDTO);

        return optionQCMRepository
            .findById(optionQCMDTO.getId())
            .map(existingOptionQCM -> {
                optionQCMMapper.partialUpdate(existingOptionQCM, optionQCMDTO);

                return existingOptionQCM;
            })
            .map(optionQCMRepository::save)
            .map(optionQCMMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OptionQCMDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OptionQCMS");
        return optionQCMRepository.findAll(pageable).map(optionQCMMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OptionQCMDTO> findOne(Long id) {
        log.debug("Request to get OptionQCM : {}", id);
        return optionQCMRepository.findById(id).map(optionQCMMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OptionQCM : {}", id);
        optionQCMRepository.deleteById(id);
    }
}
