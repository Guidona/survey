package io.survey.service.dto.export;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class QuestionnaireFormulaireSectionDTO implements Serializable {

    private Long id;

    private String name;

    private Integer ordre;

    private List<QuestionnaireFormulaireSectionDTO> sections;

    private Set<QuestionnaireLigneFormulaireDTO> lignesFormulaire;

}
