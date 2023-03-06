package io.survey.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.survey.model.Section} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SectionDTO implements Serializable {

    private Long id;

    private String name;

    private Integer ordre;

    private QuestionnaireDTO questionnaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public QuestionnaireDTO getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnaireDTO questionnaire) {
        this.questionnaire = questionnaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SectionDTO)) {
            return false;
        }

        SectionDTO sectionDTO = (SectionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sectionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SectionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", ordre=" + getOrdre() +
            ", questionnaire=" + getQuestionnaire() +
            ", questionnaire=" + getQuestionnaire() +
            "}";
    }
}
