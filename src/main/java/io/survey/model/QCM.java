package io.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("QCM")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QCM extends Question {

    @OneToMany(mappedBy = "qcm")
    @JsonIgnoreProperties(value = { "qcm" }, allowSetters = true)
    private Set<OptionQCM> optionsQCM = new HashSet<>();

    @Override
    public String toString() {
        return super.toString();
    }
}
