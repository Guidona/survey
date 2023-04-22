package io.survey.service;

import io.survey.model.Formulaire;
import io.survey.repository.FormulaireRepository;
import io.survey.service.dto.FormulaireDTO;
import io.survey.service.dto.FormulaireQueryDTO;
import io.survey.service.mapper.FormulaireMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FormulaireQueryService {

    private FormulaireRepository formulaireRepository;

    private FormulaireMapper formulaireMapper;


    public FormulaireQueryService(FormulaireRepository formulaireRepository,
                                  FormulaireMapper formulaireMapper) {
        this.formulaireRepository = formulaireRepository;
        this.formulaireMapper = formulaireMapper;
    }

    public Page<FormulaireDTO> findAll(FormulaireQueryDTO formulaireQueryDTO, Pageable pageable) {
        final Specification<Formulaire> specification = createSpecification(formulaireQueryDTO);
        return formulaireRepository.findAll(specification, pageable)
                .map(formulaireMapper::toDto);
    }

    Specification<Formulaire> createSpecification(FormulaireQueryDTO formulaireQueryDTO) {
        Specification<Formulaire> specification = Specification.where(null);
        if(formulaireQueryDTO != null) {
            if(formulaireQueryDTO.getFormulaireId() != null) {
                specification = specification.and(((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("formualaireId"), formulaireQueryDTO.getFormulaireId())
                ));
            }
            if(formulaireQueryDTO.getReference() != null) {
                specification = specification.and(((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("reference"), formulaireQueryDTO.getReference())
                ));
            }
        }
        return specification;
    }

}
