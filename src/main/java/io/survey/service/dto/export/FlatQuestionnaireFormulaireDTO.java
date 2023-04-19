package io.survey.service.dto.export;

import lombok.Data;

import java.util.Set;

@Data
public class FlatQuestionnaireFormulaireDTO {

    private Long formulaireId;

    private Long questionnaireId;

    private String name;

    private Integer numero;

    private Set<QuestionnaireLigneFormulaireDTO> lignesFormulaire;
}
