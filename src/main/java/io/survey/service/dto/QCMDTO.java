package io.survey.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.survey.model.QCM} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QCMDTO extends QuestionDTO implements Serializable {

    private Long id;

    private SectionDTO section;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", question=" + getQuestion() +
                "}";
    }
}
