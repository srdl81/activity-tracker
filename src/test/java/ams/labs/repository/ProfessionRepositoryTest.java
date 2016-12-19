package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.Profession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class ProfessionRepositoryTest {

    private static final String SYSTEMUTVECKLARE = "Systemutvecklare";
    private static final String PROFESSION_ID = "2255";
    @Autowired
    private ProfessionRepository repository;

    @Test
    public void findByProfessionId() throws Exception {
        //Given:
        Profession profession = new Profession();
        profession.setName(SYSTEMUTVECKLARE);
        profession.setProfessionId(PROFESSION_ID);
        repository.save(profession);

        //When:
        Profession result = repository.findByProfessionId(PROFESSION_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getProfessionId()).isEqualTo(PROFESSION_ID);
        assertThat(result.getName()).isEqualTo(SYSTEMUTVECKLARE);
    }

}