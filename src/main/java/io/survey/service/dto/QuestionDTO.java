package io.survey.service.dto;

import java.io.Serializable;
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

    private QuestionDTO question;

    private SectionDTO section;

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

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public SectionDTO getSection() {
        return section;
    }

    public void setSection(SectionDTO section) {
        this.section = section;
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
            ", question=" + getQuestion() +
            ", section=" + getSection() +
            ", section=" + getSection() +
            "}";
    }
}
