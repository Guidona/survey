package io.survey.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.survey.model.Reponse} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReponseDTO implements Serializable {

    private Long id;

    private String contenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReponseDTO)) {
            return false;
        }

        ReponseDTO reponseDTO = (ReponseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, reponseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReponseDTO{" +
            "id=" + getId() +
            ", contenu='" + getContenu() + "'" +
            "}";
    }
}
