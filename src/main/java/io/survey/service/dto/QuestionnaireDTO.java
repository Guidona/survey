package io.survey.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link io.survey.model.Questionnaire} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionnaireDTO implements Serializable {

    private Long id;

    private String name;

    private Integer numero;

    private Set<SectionDTO> sections;

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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Set<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(Set<SectionDTO> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionnaireDTO)) {
            return false;
        }

        QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionnaireDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionnaireDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
