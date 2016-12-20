package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.JobAdvertisement;
import ams.labs.entity.Watched;
import ams.labs.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class UserRepositoryTest {

    private static final String JOB_ADVERTISEMENT_ID = "6968822";
    @Autowired
    private UserRepository repository;

    @Autowired
    private WatchedRepository watchedRepository;

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
    public void userWatched() {

        //Given:
        User user = new User();
        user.setUserId(USER_ID);

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        jobAdvertisement.setJobAdvertisementId(JOB_ADVERTISEMENT_ID);

        Watched watched = new Watched(user, jobAdvertisement, getCurrentDate());
        watchedRepository.save(watched);

        //When:
        User result = repository.findByUserId(USER_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(USER_ID);
        assertThat(result.getWatched().get(0).getJobAdvertisement().getJobAdvertisementId()).isEqualTo(JOB_ADVERTISEMENT_ID);
    }

    @Test
    public void cafeShouldNeverServeCoffeeItDoesntHave() {
       //Given:
        User user = new User();
        user.setUserId(USER_ID);

        JobAdvertisement job = new JobAdvertisement();
        job.setJobAdvertisementId(JOB_ADVERTISEMENT_ID);

        repository.save(user);

        watchedRepository.save(new Watched(user, job, getCurrentDate()));

    }

    private Date getCurrentDate() {
        return Date.from(Instant.now());
    }

}