package io.survey.service.mapper.export;

import io.survey.model.Formulaire;
import io.survey.model.Section;
import io.survey.repository.LigneFormulaireRepository;
import io.survey.service.dto.export.QuestionnaireFormulaireDTO;
import io.survey.service.dto.export.QuestionnaireFormulaireSectionDTO;
import io.survey.service.mapper.EntityMapper;
import io.survey.service.mapper.SectionMapper;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {QuestionnaireLigneFormulaireMapper.class, SectionMapper.class})
public interface QuestionnaireFormulaireSectionMapper extends EntityMapper<QuestionnaireFormulaireSectionDTO, Section> {

    QuestionnaireFormulaireSectionDTO toDto(Section s);

}
