package io.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "QuestionType")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "code")
    private String code;

    @Column(name = "obligatoire")
    private Boolean obligatoire;

    @ManyToOne
    @JsonIgnoreProperties(value = { "question", "section" })
    private Question question;

    @ManyToOne
    @JsonIgnoreProperties(value = { "questions" })
    private Section section;

}
