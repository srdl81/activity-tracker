package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.Annons;
import ams.labs.entity.Tittat;
import ams.labs.entity.Anvandare;
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
public class AnvandareRepositoryTest {

    private static final String JOB_ADVERTISEMENT_ID = "6968822";
    @Autowired
    private AnvardarRepository repository;

    @Autowired
    private TittatRepository tittatRepository;

    private static final Long USER_ID = new Long(10000001);

    @Test
    public void findByUserId() throws Exception {
        //Given:
        Anvandare anvandare = new Anvandare();
        anvandare.setUserId(USER_ID);
        repository.save(anvandare);

        //When:
        Anvandare result = repository.findByUserId(USER_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(USER_ID);
    }


    @Test
    public void userWatched() {

        //Given:
        Anvandare anvandare = new Anvandare();
        anvandare.setUserId(USER_ID);

        Annons annons = new Annons();
        annons.setAnnonsId(JOB_ADVERTISEMENT_ID);

        Tittat tittat = new Tittat(anvandare, annons, getCurrentDate());
        tittatRepository.save(tittat);

        //When:
        Anvandare result = repository.findByUserId(USER_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(USER_ID);
        assertThat(result.getTittat().get(0).getAnnons().getAnnonsId()).isEqualTo(JOB_ADVERTISEMENT_ID);
    }

    @Test
    public void cafeShouldNeverServeCoffeeItDoesntHave() {
       //Given:
        Anvandare anvandare = new Anvandare();
        anvandare.setUserId(USER_ID);

        Annons job = new Annons();
        job.setAnnonsId(JOB_ADVERTISEMENT_ID);

        repository.save(anvandare);

        tittatRepository.save(new Tittat(anvandare, job, getCurrentDate()));

    }

    private Date getCurrentDate() {
        return Date.from(Instant.now());
    }

}