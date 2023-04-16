package io.survey.repository;

import io.survey.model.Section;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Section entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findByQuestionnaire_IdOrderByOrdreAsc(Long questionnaireId);

}
