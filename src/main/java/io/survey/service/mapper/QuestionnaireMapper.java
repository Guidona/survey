package io.survey.service.mapper;

import io.survey.model.Questionnaire;
import io.survey.service.dto.QuestionnaireDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Questionnaire} and its DTO {@link QuestionnaireDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionnaireMapper extends EntityMapper<QuestionnaireDTO, Questionnaire> {}
