package io.survey.repository;

import io.survey.model.QCM;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QCM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QCMRepository extends JpaRepository<QCM, Long> {}
