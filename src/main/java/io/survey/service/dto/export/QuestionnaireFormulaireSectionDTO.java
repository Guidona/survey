package io.survey.service.dto.export;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionnaireFormulaireSectionDTO implements Serializable {

    private Long id;

    private String name;

    private Integer ordre;

    private List<QuestionnaireFormulaireSectionDTO> sections;

    private List<QuestionnaireLigneFormulaireDTO> lignesFormulaire;

}
