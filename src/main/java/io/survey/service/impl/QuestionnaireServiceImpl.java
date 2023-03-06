package io.survey.service.impl;

import io.survey.model.Questionnaire;
import io.survey.repository.QuestionnaireRepository;
import io.survey.service.QuestionnaireService;
import io.survey.service.dto.QuestionnaireDTO;
import io.survey.service.mapper.QuestionnaireMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Questionnaire}.
 */
@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final Logger log = LoggerFactory.getLogger(QuestionnaireServiceImpl.class);

    private final QuestionnaireRepository questionnaireRepository;

    private final QuestionnaireMapper questionnaireMapper;

    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository, QuestionnaireMapper questionnaireMapper) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionnaireMapper = questionnaireMapper;
    }

    @Override
    public QuestionnaireDTO save(QuestionnaireDTO questionnaireDTO) {
        log.debug("Request to save Questionnaire : {}", questionnaireDTO);
        Questionnaire questionnaire = questionnaireMapper.toEntity(questionnaireDTO);
        questionnaire = questionnaireRepository.save(questionnaire);
        return questionnaireMapper.toDto(questionnaire);
    }

    @Override
    public QuestionnaireDTO update(QuestionnaireDTO questionnaireDTO) {
        log.debug("Request to update Questionnaire : {}", questionnaireDTO);
        Questionnaire questionnaire = questionnaireMapper.toEntity(questionnaireDTO);
        questionnaire = questionnaireRepository.save(questionnaire);
        return questionnaireMapper.toDto(questionnaire);
    }

    @Override
    public Optional<QuestionnaireDTO> partialUpdate(QuestionnaireDTO questionnaireDTO) {
        log.debug("Request to partially update Questionnaire : {}", questionnaireDTO);

        return questionnaireRepository
            .findById(questionnaireDTO.getId())
            .map(existingQuestionnaire -> {
                questionnaireMapper.partialUpdate(existingQuestionnaire, questionnaireDTO);

                return existingQuestionnaire;
            })
            .map(questionnaireRepository::save)
            .map(questionnaireMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionnaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Questionnaires");
        return questionnaireRepository.findAll(pageable).map(questionnaireMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionnaireDTO> findOne(Long id) {
        log.debug("Request to get Questionnaire : {}", id);
        return questionnaireRepository.findById(id).map(questionnaireMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Questionnaire : {}", id);
        questionnaireRepository.deleteById(id);
    }
}
