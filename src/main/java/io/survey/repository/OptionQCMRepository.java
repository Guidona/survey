package io.survey.repository;

import io.survey.model.OptionQCM;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OptionQCM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OptionQCMRepository extends JpaRepository<OptionQCM, Long> {}
