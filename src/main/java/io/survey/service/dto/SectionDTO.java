package io.survey.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link io.survey.model.Section} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SectionDTO implements Serializable {

    private Long id;

    private String name;

    private Integer ordre;

    private List<Map<String, Object>> questionsObject = new ArrayList<>();

    private List<SectionDTO> sections;

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

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
    }

    public List<Map<String, Object>> getQuestionsObject() {
        return questionsObject;
    }

    public void setQuestionsObject(List<Map<String, Object>> questionsObject) {
        this.questionsObject = questionsObject;
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
            "}";
    }
}
