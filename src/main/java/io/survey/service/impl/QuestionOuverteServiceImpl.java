package io.survey.service.impl;

import io.survey.model.QuestionOuverte;
import io.survey.repository.QuestionOuverteRepository;
import io.survey.service.QuestionOuverteService;
import io.survey.service.dto.QuestionOuverteDTO;
import io.survey.service.mapper.QuestionOuverteMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionOuverte}.
 */
@Service
@Transactional
public class QuestionOuverteServiceImpl implements QuestionOuverteService {

    private final Logger log = LoggerFactory.getLogger(QuestionOuverteServiceImpl.class);

    private final QuestionOuverteRepository questionOuverteRepository;

    private final QuestionOuverteMapper questionOuverteMapper;

    public QuestionOuverteServiceImpl(QuestionOuverteRepository questionOuverteRepository, QuestionOuverteMapper questionOuverteMapper) {
        this.questionOuverteRepository = questionOuverteRepository;
        this.questionOuverteMapper = questionOuverteMapper;
    }

    @Override
    public QuestionOuverteDTO save(QuestionOuverteDTO questionOuverteDTO) {
        log.debug("Request to save QuestionOuverte : {}", questionOuverteDTO);
        QuestionOuverte questionOuverte = questionOuverteMapper.toEntity(questionOuverteDTO);
        questionOuverte = questionOuverteRepository.save(questionOuverte);
        return questionOuverteMapper.toDto(questionOuverte);
    }

    @Override
    public QuestionOuverteDTO update(QuestionOuverteDTO questionOuverteDTO) {
        log.debug("Request to update QuestionOuverte : {}", questionOuverteDTO);
        QuestionOuverte questionOuverte = questionOuverteMapper.toEntity(questionOuverteDTO);
        questionOuverte = questionOuverteRepository.save(questionOuverte);
        return questionOuverteMapper.toDto(questionOuverte);
    }

    @Override
    public Optional<QuestionOuverteDTO> partialUpdate(QuestionOuverteDTO questionOuverteDTO) {
        log.debug("Request to partially update QuestionOuverte : {}", questionOuverteDTO);

        return questionOuverteRepository
            .findById(questionOuverteDTO.getId())
            .map(existingQuestionOuverte -> {
                questionOuverteMapper.partialUpdate(existingQuestionOuverte, questionOuverteDTO);

                return existingQuestionOuverte;
            })
            .map(questionOuverteMapper::toDto)
                .map(this::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionOuverteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuestionOuvertes");
        return questionOuverteRepository.findAll(pageable).map(questionOuverteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionOuverteDTO> findOne(Long id) {
        log.debug("Request to get QuestionOuverte : {}", id);
        return questionOuverteRepository.findById(id).map(questionOuverteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuestionOuverte : {}", id);
        questionOuverteRepository.deleteById(id);
    }
}
