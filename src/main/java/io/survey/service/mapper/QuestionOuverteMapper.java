package io.survey.service.mapper;

import io.survey.model.Question;
import io.survey.model.QuestionOuverte;
import io.survey.model.Section;
import io.survey.service.dto.QuestionDTO;
import io.survey.service.dto.QuestionOuverteDTO;
import io.survey.service.dto.SectionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionOuverte} and its DTO {@link QuestionOuverteDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionOuverteMapper extends EntityMapper<QuestionOuverteDTO, QuestionOuverte> {
    @Mapping(target = "question", source = "question", qualifiedByName = "questionId")
    @Mapping(target = "section", source = "section", qualifiedByName = "sectionId")
    QuestionDTO toDto(Question s);

    QuestionOuverteDTO toDto(QuestionOuverte questionOuverte);

    @Named("questionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionDTO toDtoQuestionId(Question question);

    @Named("sectionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SectionDTO toDtoSectionId(Section section);

}
