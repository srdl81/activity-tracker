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

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class UserRepositoryTest {

    private static final Long JOB_ADVERTISEMENT_ID = new Long(1234567);
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
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(USER_ID);
    }


    @Test
    public void userLookedAt() {

        //Given:
        User user = new User();
        user.setUserId(USER_ID);

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        jobAdvertisement.setJobAdvertisementId(JOB_ADVERTISEMENT_ID);

        user.lookedAt(jobAdvertisement);
        repository.save(user);

        //When:
        User result = repository.findByUserId(USER_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(USER_ID);
        assertThat(result.getJobAdvertisements().get(0).getJobAdvertisementId()).isEqualTo(JOB_ADVERTISEMENT_ID);
    }

}