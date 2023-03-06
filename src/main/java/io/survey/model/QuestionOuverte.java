package io.survey.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DiscriminatorValue("QuestionOuverte")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionOuverte extends Question {

}
