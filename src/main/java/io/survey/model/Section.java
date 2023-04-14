package io.survey.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"questionnaire"})
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ordre")
    private Integer ordre;

    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private Questionnaire questionnaire;

    @ManyToOne
    @JsonBackReference
    private Section section;


    @OneToMany(mappedBy = "section")
    private List<Section> sections = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private OptionQCM dependsOn;

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ordre=" + ordre +
                ", questions=" + questions +
                '}';
    }
}
