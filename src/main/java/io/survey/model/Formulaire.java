package io.survey.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formulaire", uniqueConstraints =
    @UniqueConstraint(name = "libelle_questionnaire", columnNames = {"questionnaire_id", "numero"})
)
public class Formulaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "numero")
    private Integer numero;

    @OneToMany(mappedBy = "formulaire")
    @JsonIgnoreProperties(value = { "formulaire", "question", "reponse", "formulaire" }, allowSetters = true)
    private List<LigneFormulaire> ligneFormulaires = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "formulaire_section",
            joinColumns = { @JoinColumn(name = "formualaire_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "section_id", referencedColumnName = "id") }
    )
    private Set<Section> sections = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Questionnaire questionnaire;

    @Column(name = "reference", nullable = true)
    private String reference;

    @Override
    public String toString() {
        return "Formulaire{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", numero=" + numero +
                '}';
    }
}
