package io.survey.service.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.survey.model.QCM;
import io.survey.model.QuestionOuverte;
import io.survey.model.Questionnaire;
import io.survey.model.Section;
import io.survey.service.dto.QuestionnaireDTO;
import io.survey.service.dto.SectionDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link Section} and its DTO {@link SectionDTO}.
 */
@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface SectionMapper extends EntityMapper<SectionDTO, Section> {
    //@Mapping(target = "section.section.section", ignore = true)
    SectionDTO toDto(Section s);

    @Named("questionnaireId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionnaireDTO toDtoQuestionnaireId(Questionnaire questionnaire);

    @AfterMapping
    default void enrichDTOWithQuestion(Section section, @MappingTarget SectionDTO sectionDTO) {
        ObjectMapper mapper = new ObjectMapper();
        sectionDTO.setQuestionsObject(
                section.getQuestions().stream().map(question -> {
                    if (question instanceof QCM)
                        return mapper.convertValue((QCM) question, new TypeReference<Map<String, Object>>() {});
                    else
                        return mapper.convertValue((QuestionOuverte) question, new TypeReference<Map<String, Object>>() {});
                }).collect(Collectors.toList()));
    }

}
