package io.survey.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link io.survey.model.QCM} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QCMDTO extends QuestionDTO implements Serializable {

    private Long id;

    @JsonIgnoreProperties(value = {"questionnaire", "questions", "section"})
    private SectionDTO section;

    private Set<OptionQCMDTO> optionsQCM;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<OptionQCMDTO> getOptionsQCM() {
        return optionsQCM;
    }

    public void setOptionsQCM(Set<OptionQCMDTO> optionsQCM) {
        this.optionsQCM = optionsQCM;
    }

    @Override
    public SectionDTO getSection() {
        return section;
    }

    @Override
    public void setSection(SectionDTO section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QCMDTO)) {
            return false;
        }

        QCMDTO qCMDTO = (QCMDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, qCMDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "QCMDTO{" +
                "id=" + id  +
                ", libelle='" + getLibelle() + "'" +
                ", ordre=" + getOrdre() +
                ", code='" + getCode() + "'" +
                ", obligatoire='" + getObligatoire() + "'" +
                "}";
    }
}
