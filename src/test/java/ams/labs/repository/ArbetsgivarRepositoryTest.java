package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.Arbetsgivare;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class ArbetsgivarRepositoryTest {

    private static final Long AMS_EMPLOYER_ID = new Long(20383691);
    private static final Long AMS_REG_NR = new Long(2021005646);
    private static final String AMS_NAME = "Kronofogdemyndigheten";

    @Autowired
    private ArbetsgivarRepository repository;

    @Test
    public void findByEmployerId() throws Exception {
        //Given:
        Arbetsgivare arbetsgivare = new Arbetsgivare();
        arbetsgivare.setNamn(AMS_NAME);
        arbetsgivare.setOrganisationsnummer(AMS_REG_NR);
        arbetsgivare.setArbetsgivarId(AMS_EMPLOYER_ID);
        repository.save(arbetsgivare);

        //When:
        Arbetsgivare result = repository.findByArbetsgivarId(AMS_EMPLOYER_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(arbetsgivare.getArbetsgivarId()).isEqualTo(AMS_EMPLOYER_ID);
        assertThat(arbetsgivare.getNamn()).isEqualTo(AMS_NAME);
        assertThat(arbetsgivare.getOrganisationsnummer()).isEqualTo(AMS_REG_NR);
    }

}