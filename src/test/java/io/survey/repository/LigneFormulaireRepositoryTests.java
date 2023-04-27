package io.survey.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LigneFormulaireRepositoryTests {

    @Autowired
    LigneFormulaireRepository ligneFormulaireRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void contextLoads() {
        assertThat(ligneFormulaireRepository).isNotNull();
        assertThat(ligneFormulaireRepository.existsByFormulaire_IdAndQuestion_Id(any(), any())).isFalse();
    }
}
