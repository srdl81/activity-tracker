package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.Yrke;
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
public class YrkeRepositoryTest {

    private static final String SYSTEMUTVECKLARE = "Systemutvecklare";
    private static final String PROFESSION_ID = "2255";
    @Autowired
    private YrkeRepository repository;

    @Test
    public void findByProfessionId() throws Exception {
        //Given:
        Yrke yrke = new Yrke();
        yrke.setName(SYSTEMUTVECKLARE);
        yrke.setYrkeId(PROFESSION_ID);
        repository.save(yrke);

        //When:
        Yrke result = repository.findByYrkeId(PROFESSION_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getYrkeId()).isEqualTo(PROFESSION_ID);
        assertThat(result.getName()).isEqualTo(SYSTEMUTVECKLARE);
    }

}