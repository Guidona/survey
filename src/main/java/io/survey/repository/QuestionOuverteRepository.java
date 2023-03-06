package io.survey.repository;

import io.survey.model.QuestionOuverte;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QuestionOuverte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionOuverteRepository extends JpaRepository<QuestionOuverte, Long> {}
