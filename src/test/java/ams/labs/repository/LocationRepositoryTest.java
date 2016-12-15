package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class LocationRepositoryTest {

    private static final String STOCKHOLM = "Stockholm";
    private static final Long STOCKHOLM_LOCATION_ID = new Long("180");

    @Autowired
    private LocationRepository repository;

    @Test
    public void findByLocationId() throws Exception {
        //Given:
        Location location = new Location();
        location.setName(STOCKHOLM);
        location.setLocationId(STOCKHOLM_LOCATION_ID);
        repository.save(location);

        //When:
        Location result = repository.findByLocationId(STOCKHOLM_LOCATION_ID);

        //Then:
        assertNotNull(result);
        assertEquals(location, result);
    }

}