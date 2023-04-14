package io.survey.repository;

import io.survey.model.Formulaire;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Formulaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormulaireRepository extends JpaRepository<Formulaire, Long> {

    List<Formulaire> findByQuestionnaire_Id(Long questionnaireId);

}
