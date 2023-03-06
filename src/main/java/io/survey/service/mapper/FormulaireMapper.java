package io.survey.service.mapper;

import io.survey.model.Formulaire;
import io.survey.service.dto.FormulaireDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Formulaire} and its DTO {@link FormulaireDTO}.
 */
@Mapper(componentModel = "spring")
public interface FormulaireMapper extends EntityMapper<FormulaireDTO, Formulaire> {}
