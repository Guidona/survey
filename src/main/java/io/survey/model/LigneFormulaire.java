package io.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ligne_formulaire", uniqueConstraints =
    @UniqueConstraint(name = "ligne_question_formulaire", columnNames = {"formulaire_id", "question_id"})
)
public class LigneFormulaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ligneFormulaires" }, allowSetters = true)
    private Formulaire formulaire;

    @ManyToOne
    @JsonIgnoreProperties(value = { "question", "section", "section" }, allowSetters = true)
    private Question question;

    @ManyToOne
    private Reponse reponse;

    @Column(name = "contenu")
    private String contenu;

    @Override
    public String toString() {
        return "LigneFormulaire{" +
                "id=" + id +
                ", formulaire=" + formulaire +
                ", question=" + question +
                ", reponse=" + reponse +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
