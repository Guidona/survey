package io.survey.repository;

import io.survey.model.LigneFormulaire;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LigneFormulaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneFormulaireRepository extends JpaRepository<LigneFormulaire, Long> {}
