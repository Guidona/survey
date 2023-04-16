package io.survey.service.mapper.export;

import io.survey.model.Formulaire;
import io.survey.service.dto.export.QuestionnaireFormulaireDTO;
import io.survey.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {QuestionnaireFormulaireSectionMapper.class})
public interface QuestionnaireFormulaireMapper extends EntityMapper<QuestionnaireFormulaireDTO, Formulaire> {

    @Mapping(target = "formulaireId", source = "id")
    @Mapping(target = "questionnaireId", source = "questionnaire.id")
    @Mapping(target = "name", source = "libelle")
    @Mapping(target = "sections", ignore = true)
    QuestionnaireFormulaireDTO toDto(Formulaire formulaire);
}
