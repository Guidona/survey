package io.survey.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link io.survey.model.Formulaire} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FormulaireDTO implements Serializable {

    private Long id;

    private String libelle;

    private Integer numero;

    @JsonIgnore
    private Set<SectionDTO> sections = new HashSet<>();

    private List<LigneFormulaireDTO> ligneFormulaires = new ArrayList<>();

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

    public Set<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(Set<SectionDTO> sections) {
        this.sections = sections;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public List<LigneFormulaireDTO> getLigneFormulaires() {
        return ligneFormulaires;
    }

    public void setLigneFormulaires(List<LigneFormulaireDTO> ligneFormulaires) {
        this.ligneFormulaires = ligneFormulaires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormulaireDTO)) {
            return false;
        }

        FormulaireDTO formulaireDTO = (FormulaireDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, formulaireDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormulaireDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
