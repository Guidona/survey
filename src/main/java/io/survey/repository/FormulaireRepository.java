package io.survey.repository;

import io.survey.model.Formulaire;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Formulaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormulaireRepository extends JpaRepository<Formulaire, Long> {}
