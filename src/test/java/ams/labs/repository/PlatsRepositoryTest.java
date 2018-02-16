package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.Plats;
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
public class PlatsRepositoryTest {

    private static final String STOCKHOLM = "Stockholm";
    private static final String STOCKHOLM_LOCATION_ID = "0180";

    @Autowired
    private PlatsRepository repository;

    @Test
    public void findByLocationId() throws Exception {
        //Given:
        Plats plats = new Plats();
        plats.setNamn(STOCKHOLM);
        plats.setPlatsId(STOCKHOLM_LOCATION_ID);
        repository.save(plats);

        //When:
        Plats result = repository.findByPlatsId(STOCKHOLM_LOCATION_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getPlatsId()).isEqualTo(STOCKHOLM_LOCATION_ID);
        assertThat(result.getNamn()).isEqualTo(STOCKHOLM);
    }

}