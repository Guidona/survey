package io.survey.service.mapper.export;

import io.survey.model.LigneFormulaire;
import io.survey.service.dto.export.QuestionnaireLigneFormulaireDTO;
import io.survey.service.mapper.EntityMapper;
import io.survey.service.mapper.QuestionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface QuestionnaireLigneFormulaireMapper extends EntityMapper<QuestionnaireLigneFormulaireDTO, LigneFormulaire> {

    @Mapping(target = "code", source = "question.code")
    @Mapping(target = "libelle", source = "question.libelle")
    @Mapping(target = "contenu", source = "contenu")
    // @Mapping(target = "", source = "")
    QuestionnaireLigneFormulaireDTO toDto(LigneFormulaire s);
}
