package io.survey.repository;

import io.survey.model.LigneFormulaire;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the LigneFormulaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneFormulaireRepository extends JpaRepository<LigneFormulaire, Long> {

    List<LigneFormulaire> findByFormulaire_Id(Long formulaireId);

}
