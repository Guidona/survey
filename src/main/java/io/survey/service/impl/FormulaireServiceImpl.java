package io.survey.service.impl;

import io.survey.model.Formulaire;
import io.survey.repository.FormulaireRepository;
import io.survey.service.FormulaireService;
import io.survey.service.dto.FormulaireDTO;
import io.survey.service.mapper.FormulaireMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Formulaire}.
 */
@Service
@Transactional
public class FormulaireServiceImpl implements FormulaireService {

    private final Logger log = LoggerFactory.getLogger(FormulaireServiceImpl.class);

    private final FormulaireRepository formulaireRepository;

    private final FormulaireMapper formulaireMapper;

    public FormulaireServiceImpl(FormulaireRepository formulaireRepository, FormulaireMapper formulaireMapper) {
        this.formulaireRepository = formulaireRepository;
        this.formulaireMapper = formulaireMapper;
    }

    @Override
    public FormulaireDTO save(FormulaireDTO formulaireDTO) {
        log.debug("Request to save Formulaire : {}", formulaireDTO);
        Formulaire formulaire = formulaireMapper.toEntity(formulaireDTO);
        formulaire = formulaireRepository.save(formulaire);
        return formulaireMapper.toDto(formulaire);
    }

    @Override
    public FormulaireDTO update(FormulaireDTO formulaireDTO) {
        log.debug("Request to update Formulaire : {}", formulaireDTO);
        Formulaire formulaire = formulaireMapper.toEntity(formulaireDTO);
        formulaire = formulaireRepository.save(formulaire);
        return formulaireMapper.toDto(formulaire);
    }

    @Override
    public Optional<FormulaireDTO> partialUpdate(FormulaireDTO formulaireDTO) {
        log.debug("Request to partially update Formulaire : {}", formulaireDTO);

        return formulaireRepository
            .findById(formulaireDTO.getId())
            .map(existingFormulaire -> {
                formulaireMapper.partialUpdate(existingFormulaire, formulaireDTO);

                return existingFormulaire;
            })
            .map(formulaireRepository::save)
            .map(formulaireMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FormulaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Formulaires");
        return formulaireRepository.findAll(pageable).map(formulaireMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormulaireDTO> findOne(Long id) {
        log.debug("Request to get Formulaire : {}", id);
        return formulaireRepository.findById(id).map(formulaireMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Formulaire : {}", id);
        formulaireRepository.deleteById(id);
    }
}
