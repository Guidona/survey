package io.survey.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.survey.model.QuestionOuverte} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionOuverteDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionOuverteDTO)) {
            return false;
        }

        QuestionOuverteDTO questionOuverteDTO = (QuestionOuverteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionOuverteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionOuverteDTO{" +
            "id=" + getId() +
            "}";
    }
}
