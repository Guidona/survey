package io.survey.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.survey.model.LigneFormulaire} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LigneFormulaireDTO implements Serializable {

    private Long id;

    @JsonIgnore
    private FormulaireDTO formulaire;

    private Long formulaireId;

    private QuestionDTO question;

    private ReponseDTO reponse;

    private String contenu;

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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Long getFormulaireId() {
        return formulaireId;
    }

    public void setFormulaireId(Long formulaireId) {
        this.formulaireId = formulaireId;
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
            ", question=" + getQuestion() +
            ", reponse=" + getReponse() +
            "}";
    }
}
