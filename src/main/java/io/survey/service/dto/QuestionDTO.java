package io.survey.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.survey.model.LigneFormulaire;
import io.survey.model.Question;
import io.survey.model.enumeration.QuestionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link io.survey.model.Question} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionDTO implements Serializable {

    private Long id;

    private String libelle;

    private Integer ordre;

    private String code;

    private Boolean obligatoire;

    private List<QuestionDTO> questions = new ArrayList<>();

    @JsonIgnore
    private SectionDTO section;

    private OptionQCMDTO dependsOn;

    private QuestionType questionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public SectionDTO getSection() {
        return section;
    }

    public void setSection(SectionDTO section) {
        this.section = section;
    }

    public OptionQCMDTO getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(OptionQCMDTO dependsOn) {
        this.dependsOn = dependsOn;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionDTO)) {
            return false;
        }

        QuestionDTO questionDTO = (QuestionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", ordre=" + getOrdre() +
            ", code='" + getCode() + "'" +
            ", obligatoire='" + getObligatoire() + "'" +
            "}";
    }
}
