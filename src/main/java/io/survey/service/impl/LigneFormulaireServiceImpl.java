package io.survey.service.impl;

import io.survey.model.LigneFormulaire;
import io.survey.model.Question;
import io.survey.repository.LigneFormulaireRepository;
import io.survey.repository.QuestionRepository;
import io.survey.service.LigneFormulaireService;
import io.survey.service.dto.LigneFormulaireDTO;
import io.survey.service.mapper.LigneFormulaireMapper;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LigneFormulaire}.
 */
@Service
@Transactional
public class LigneFormulaireServiceImpl implements LigneFormulaireService {

    private final Logger log = LoggerFactory.getLogger(LigneFormulaireServiceImpl.class);

    private final LigneFormulaireRepository ligneFormulaireRepository;

    private final LigneFormulaireMapper ligneFormulaireMapper;

    private final QuestionRepository questionRepository;

    public LigneFormulaireServiceImpl(LigneFormulaireRepository ligneFormulaireRepository,
                                      LigneFormulaireMapper ligneFormulaireMapper,
                                      QuestionRepository questionRepository) {
        this.ligneFormulaireRepository = ligneFormulaireRepository;
        this.ligneFormulaireMapper = ligneFormulaireMapper;
        this.questionRepository = questionRepository;
    }

    @Override
    public LigneFormulaireDTO save(LigneFormulaireDTO ligneFormulaireDTO) {
        log.debug("Request to save LigneFormulaire : {}", ligneFormulaireDTO);
        LigneFormulaire ligneFormulaire = ligneFormulaireMapper.toEntity(ligneFormulaireDTO);
        ligneFormulaire = ligneFormulaireRepository.save(ligneFormulaire);

        final LigneFormulaire finalLigneFormulaire = ligneFormulaire;
        CompletableFuture.runAsync(() -> this.saveAll(finalLigneFormulaire));

        return ligneFormulaireMapper.toDto(ligneFormulaire);
    }

    @Override
    public LigneFormulaireDTO update(LigneFormulaireDTO ligneFormulaireDTO) {
        log.debug("Request to update LigneFormulaire : {}", ligneFormulaireDTO);
        LigneFormulaire ligneFormulaire = ligneFormulaireMapper.toEntity(ligneFormulaireDTO);
        ligneFormulaire = ligneFormulaireRepository.save(ligneFormulaire);

        final LigneFormulaire finalLigneFormulaire = ligneFormulaire;
        CompletableFuture.runAsync(() -> this.saveAll(finalLigneFormulaire));

        return ligneFormulaireMapper.toDto(ligneFormulaire);
    }

    // Recursive function to save all unsaved parent questions of the given question
    // - Important for questionnaire export
    private void saveAll(LigneFormulaire ligneFormulaire) {
        questionRepository.findById(ligneFormulaire.getId()).ifPresent(question -> {
            if(question.getQuestion() != null) {
                if(!ligneFormulaireRepository.existsByFormulaire_IdAndQuestion_Id(
                        ligneFormulaire.getFormulaire().getId(),
                        question.getQuestion().getId())) {
                    LigneFormulaire ligne = new LigneFormulaire();
                    ligne.setFormulaire(ligneFormulaire.getFormulaire());
                    ligne.setContenu("");
                    ligne.setQuestion(question.getQuestion());
                    ligne = ligneFormulaireRepository.save(ligne);
                    saveAll(ligne);
                }
            }
        });
    }

    @Override
    public Optional<LigneFormulaireDTO> partialUpdate(LigneFormulaireDTO ligneFormulaireDTO) {
        log.debug("Request to partially update LigneFormulaire : {}", ligneFormulaireDTO);

        return ligneFormulaireRepository
            .findById(ligneFormulaireDTO.getId())
            .map(existingLigneFormulaire -> {
                ligneFormulaireMapper.partialUpdate(existingLigneFormulaire, ligneFormulaireDTO);

                return existingLigneFormulaire;
            })
            .map(ligneFormulaireRepository::save)
            .map(ligneFormulaireMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LigneFormulaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LigneFormulaires");
        return ligneFormulaireRepository.findAll(pageable).map(ligneFormulaireMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LigneFormulaireDTO> findOne(Long id) {
        log.debug("Request to get LigneFormulaire : {}", id);
        return ligneFormulaireRepository.findById(id).map(ligneFormulaireMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LigneFormulaire : {}", id);
        ligneFormulaireRepository.deleteById(id);
    }

    @Override
    public List<LigneFormulaireDTO> findByFormulaire(Long formulaireId) {
        return ligneFormulaireMapper.toDto(ligneFormulaireRepository.findByFormulaire_Id(formulaireId));
    }
}
