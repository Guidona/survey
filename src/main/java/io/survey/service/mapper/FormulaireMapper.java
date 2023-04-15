package io.survey.service.mapper;

import io.survey.model.Formulaire;
import io.survey.service.dto.FormulaireDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Formulaire} and its DTO {@link FormulaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionnaireMapper.class})
public interface FormulaireMapper extends EntityMapper<FormulaireDTO, Formulaire> {

    @Mapping(target = "questionnaireId", source = "questionnaire.id")
    FormulaireDTO toDto(Formulaire s);

    @Mapping(target = "questionnaire.id", source = "questionnaireId")
    Formulaire toEntity(FormulaireDTO s);

    default Formulaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Formulaire formulaire = new Formulaire();
        formulaire.setId(id);
        return formulaire;
    }

}
