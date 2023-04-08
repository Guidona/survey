package io.survey.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Question question;

    @OneToMany(mappedBy = "question")
    private List<Question> questions = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private Section section;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", ordre=" + ordre +
                ", code='" + code + '\'' +
                ", obligatoire=" + obligatoire +
                '}';
    }
}
