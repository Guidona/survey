package io.survey.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.survey.model.OptionQCM} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OptionQCMDTO implements Serializable {

    private Long id;

    private String libelle;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OptionQCMDTO)) {
            return false;
        }

        OptionQCMDTO optionQCMDTO = (OptionQCMDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, optionQCMDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OptionQCMDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
