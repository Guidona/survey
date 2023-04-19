package io.survey.service.dto.export;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class QuestionnaireLigneFormulaireDTO implements Serializable {

    private Long id;

    private String code;

    private String libelle;

    private String contenu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionnaireLigneFormulaireDTO that = (QuestionnaireLigneFormulaireDTO) o;
        return Objects.equals(code, that.code) && Objects.equals(libelle, that.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, libelle);
    }


}
