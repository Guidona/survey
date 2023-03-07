package io.survey.service.mapper;

import io.survey.model.Question;
import io.survey.model.Section;
import io.survey.service.dto.QuestionDTO;
import io.survey.service.dto.SectionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionMapper extends EntityMapper<QuestionDTO, Question> {
    @Mapping(target = "question", source = "question", qualifiedByName = "questionId")
    @Mapping(target = "section", source = "section", qualifiedByName = "sectionId")
    QuestionDTO toDto(Question s);

    @Named("questionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionDTO toDtoQuestionId(Question question);

    @Named("sectionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SectionDTO toDtoSectionId(Section section);
}
