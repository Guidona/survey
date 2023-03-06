package io.survey.service.mapper;

import io.survey.model.QuestionOuverte;
import io.survey.service.dto.QuestionOuverteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionOuverte} and its DTO {@link QuestionOuverteDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionOuverteMapper extends EntityMapper<QuestionOuverteDTO, QuestionOuverte> {}
