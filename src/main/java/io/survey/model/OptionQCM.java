package io.survey.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "option_qcm")
@EqualsAndHashCode(exclude = "qcm")
public class OptionQCM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @ManyToOne
    @JsonIgnoreProperties(value = { "optionsQCM", "section", "question" })
    private QCM qcm;

    @Column(name = "is_default")
    private Boolean isDefault = false;

}
