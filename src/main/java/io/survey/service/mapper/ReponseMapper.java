package io.survey.service.mapper;

import io.survey.model.Reponse;
import io.survey.service.dto.ReponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reponse} and its DTO {@link ReponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReponseMapper extends EntityMapper<ReponseDTO, Reponse> {}
