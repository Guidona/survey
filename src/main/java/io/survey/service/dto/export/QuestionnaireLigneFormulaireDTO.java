package io.survey.service.dto.export;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionnaireLigneFormulaireDTO implements Serializable {

    private Long id;

    private String code;

    private String libelle;

    private String reponse;

}
