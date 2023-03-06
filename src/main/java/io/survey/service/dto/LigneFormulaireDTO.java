package io.survey.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.survey.model.LigneFormulaire} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LigneFormulaireDTO implements Serializable {

    private Long id;

    private FormulaireDTO formulaire;

    private QuestionDTO question;

    private ReponseDTO reponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FormulaireDTO getFormulaire() {
        return formulaire;
    }

    public void setFormulaire(FormulaireDTO formulaire) {
        this.formulaire = formulaire;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public ReponseDTO getReponse() {
        return reponse;
    }

    public void setReponse(ReponseDTO reponse) {
        this.reponse = reponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneFormulaireDTO)) {
            return false;
        }

        LigneFormulaireDTO ligneFormulaireDTO = (LigneFormulaireDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ligneFormulaireDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneFormulaireDTO{" +
            "id=" + getId() +
            ", formulaire=" + getFormulaire() +
            ", question=" + getQuestion() +
            ", reponse=" + getReponse() +
            ", formulaire=" + getFormulaire() +
            "}";
    }
}
