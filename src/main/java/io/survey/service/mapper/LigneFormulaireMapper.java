package io.survey.service.mapper;

import io.survey.model.Formulaire;
import io.survey.model.LigneFormulaire;
import io.survey.model.Question;
import io.survey.model.Reponse;
import io.survey.service.dto.FormulaireDTO;
import io.survey.service.dto.LigneFormulaireDTO;
import io.survey.service.dto.QuestionDTO;
import io.survey.service.dto.ReponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LigneFormulaire} and its DTO {@link LigneFormulaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {FormulaireMapper.class})
public interface LigneFormulaireMapper extends EntityMapper<LigneFormulaireDTO, LigneFormulaire> {
    @Mapping(target = "formulaireId", source = "formulaire.id")
    LigneFormulaireDTO toDto(LigneFormulaire s);

    @Mapping(target = "formulaire.id", source = "formulaireId")
    LigneFormulaire toEntity(LigneFormulaireDTO s);

//    @Named("formulaireId")
//    @BeanMapping(ignoreByDefault = true)
//    @Mapping(target = "id", source = "id")
//    FormulaireDTO toDtoFormulaireId(Formulaire formulaire);
//
//    @Named("questionId")
//    @BeanMapping(ignoreByDefault = true)
//    @Mapping(target = "id", source = "id")
//    QuestionDTO toDtoQuestionId(Question question);
//
//    @Named("reponseId")
//    @BeanMapping(ignoreByDefault = true)
//    @Mapping(target = "id", source = "id")
//    ReponseDTO toDtoReponseId(Reponse reponse);
}
