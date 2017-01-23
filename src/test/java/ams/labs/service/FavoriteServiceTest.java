package ams.labs.service;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.dto.FavoriteDTO;
import ams.labs.entity.*;
import ams.labs.exception.ModelNotFoundException;
import ams.labs.repository.FavoriteRepository;
import ams.labs.repository.JobAdvertisementRepository;
import ams.labs.repository.UserRepository;
import org.junit.Before;
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
public class FavoriteServiceTest {

    private static final Long USER_ID = new Long(111111001);

    @Autowired
    private FavoriteService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobAdvertisementRepository jobAdvertisementRepository;

    @Before
    public void setUp() throws Exception {
        Location location = new Location();
        location.setName("Stockholm");
        location.setLocationId("0180");

        Profession profession = new Profession();
        profession.setProfessionId("7296");
        profession.setName("Sjuksköterska, grundutbildad");

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        jobAdvertisement.setJobAdvertisementId("6968823");
        jobAdvertisement.setLocation(location);
        jobAdvertisement.setProfession(profession);
        jobAdvertisementRepository.save(jobAdvertisement);

    }

    @Test
    public void saveToggledFavoriteRelation() throws Exception {
        //Given:
        service.saveFavoriteRelation(USER_ID, new FavoriteDTO("6968823", true));

        //When:
        service.saveFavoriteRelation(USER_ID, new FavoriteDTO("6968823", false));
        User user = userRepository.findByUserId(USER_ID);

        //Then:
        assertThat(user).isNotNull();
        assertThat(user.getFavorites()).isEmpty();
        assertThat(user.getFavorites().size()).isEqualTo(0);
    }

    @Test
    public void deleteToggledFavoriteRelation() throws Exception {
        //Given:
        FavoriteDTO dto = new FavoriteDTO();
        dto.setId("6968823");
        dto.setFavoriteToggled(true);

        //When:
        service.saveFavoriteRelation(USER_ID, dto);
        User user = userRepository.findByUserId(USER_ID);

        //Then:
        assertThat(user).isNotNull();

        assertThat(user.getFavorites())
                .isNotNull()
                .isNotEmpty();

        assertThat(user.getFavorites().size()).isEqualTo(1);
    }


    @Test(expected = ModelNotFoundException.class)
    public void shouldThrowException() {
       //Given:
        FavoriteDTO dto = new FavoriteDTO("9998823", false);

       //When:
        service.saveFavoriteRelation(USER_ID, dto);
    }

}