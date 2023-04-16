package io.survey.service.dto.export;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestionnaireFormulaireDTO implements Serializable {

    private Long formulaireId;

    private Long questionnaireId;

    private String name;

    private Integer numero;

    private List<QuestionnaireFormulaireSectionDTO> sections;

}
