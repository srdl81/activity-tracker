package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.JobAdvertisement;
import ams.labs.entity.User;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private static final Long USER_ID = new Long(10000001);

    @Test
    public void findByUserId() throws Exception {
        //Given:
        User user = new User();
        user.setUserId(USER_ID);
        repository.save(user);

        //When:
        User result = repository.findByUserId(USER_ID);

        //Then:
        assertNotNull(result);
        assertEquals(result, result);
    }


    @Test
    public void userLookedAt() {

        //Given:
        User user = new User();
        user.setUserId(USER_ID);

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        jobAdvertisement.setJobAdvertisementId(new Long(1234567));

        user.lookedAt(jobAdvertisement);
        repository.save(user);
        
        //When:
        User result = repository.findByUserId(USER_ID);

        //Then:
        assertNotNull(result);
        assertEquals(user, result);
        assertEquals(jobAdvertisement, result.getJobAdvertisements().get(0));

    }

}