package io.survey.service.mapper;

import io.survey.model.Questionnaire;
import io.survey.model.Section;
import io.survey.service.dto.QuestionnaireDTO;
import io.survey.service.dto.SectionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Section} and its DTO {@link SectionDTO}.
 */
@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface SectionMapper extends EntityMapper<SectionDTO, Section> {
    @Mapping(target = "questionnaire", source = "questionnaire", qualifiedByName = "questionnaireId")
    @Mapping(target = "question.question", ignore = true)
    SectionDTO toDto(Section s);

    @Named("questionnaireId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionnaireDTO toDtoQuestionnaireId(Questionnaire questionnaire);
}
