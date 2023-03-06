package io.survey.service.impl;

import io.survey.model.Reponse;
import io.survey.repository.ReponseRepository;
import io.survey.service.ReponseService;
import io.survey.service.dto.ReponseDTO;
import io.survey.service.mapper.ReponseMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Reponse}.
 */
@Service
@Transactional
public class ReponseServiceImpl implements ReponseService {

    private final Logger log = LoggerFactory.getLogger(ReponseServiceImpl.class);

    private final ReponseRepository reponseRepository;

    private final ReponseMapper reponseMapper;

    public ReponseServiceImpl(ReponseRepository reponseRepository, ReponseMapper reponseMapper) {
        this.reponseRepository = reponseRepository;
        this.reponseMapper = reponseMapper;
    }

    @Override
    public ReponseDTO save(ReponseDTO reponseDTO) {
        log.debug("Request to save Reponse : {}", reponseDTO);
        Reponse reponse = reponseMapper.toEntity(reponseDTO);
        reponse = reponseRepository.save(reponse);
        return reponseMapper.toDto(reponse);
    }

    @Override
    public ReponseDTO update(ReponseDTO reponseDTO) {
        log.debug("Request to update Reponse : {}", reponseDTO);
        Reponse reponse = reponseMapper.toEntity(reponseDTO);
        reponse = reponseRepository.save(reponse);
        return reponseMapper.toDto(reponse);
    }

    @Override
    public Optional<ReponseDTO> partialUpdate(ReponseDTO reponseDTO) {
        log.debug("Request to partially update Reponse : {}", reponseDTO);

        return reponseRepository
            .findById(reponseDTO.getId())
            .map(existingReponse -> {
                reponseMapper.partialUpdate(existingReponse, reponseDTO);

                return existingReponse;
            })
            .map(reponseRepository::save)
            .map(reponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reponses");
        return reponseRepository.findAll(pageable).map(reponseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReponseDTO> findOne(Long id) {
        log.debug("Request to get Reponse : {}", id);
        return reponseRepository.findById(id).map(reponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reponse : {}", id);
        reponseRepository.deleteById(id);
    }
}
