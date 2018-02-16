package ams.labs.service;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.dto.FavoriteDTO;
import ams.labs.entity.*;
import ams.labs.exception.ModelNotFoundException;
import ams.labs.repository.AnnonsRepository;
import ams.labs.repository.AnvardarRepository;
import org.junit.Before;
import org.junit.Ignore;
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
@Ignore
public class FavoritServiceTest {

    private static final Long USER_ID = new Long(111111001);

    @Autowired
    private FavoriteService service;

    @Autowired
    private AnvardarRepository anvardarRepository;

    @Autowired
    private AnnonsRepository annonsRepository;

    @Before
    public void setUp() throws Exception {
        Plats plats = new Plats();
        plats.setNamn("Stockholm");
        plats.setPlatsId("0180");

        Yrke yrke = new Yrke();
        yrke.setYrkeId("7296");
        yrke.setName("Sjuksköterska, grundutbildad");

        Annons annons = new Annons();
        annons.setAnnonsId("6968823");
        annons.setPlats(plats);
        annons.setYrke(yrke);
        annonsRepository.save(annons);

    }

    @Test
    public void saveToggledFavoriteRelation() throws Exception {
        //Given:
        service.saveFavoriteRelation(USER_ID, new FavoriteDTO("6968823", true));

        //When:
        service.saveFavoriteRelation(USER_ID, new FavoriteDTO("6968823", false));
        Anvandare anvandare = anvardarRepository.findByAnvandarId(USER_ID);

        //Then:
        assertThat(anvandare).isNotNull();
        assertThat(anvandare.getFavorits()).isEmpty();
        assertThat(anvandare.getFavorits().size()).isEqualTo(0);
    }

    @Test
    public void deleteToggledFavoriteRelation() throws Exception {
        //Given:
        FavoriteDTO dto = new FavoriteDTO();
        dto.setId("6968823");
        dto.setFavoriteToggled(true);

        //When:
        service.saveFavoriteRelation(USER_ID, dto);
        Anvandare anvandare = anvardarRepository.findByAnvandarId(USER_ID);

        //Then:
        assertThat(anvandare).isNotNull();

        assertThat(anvandare.getFavorits())
                .isNotNull()
                .isNotEmpty();

        assertThat(anvandare.getFavorits().size()).isEqualTo(1);
    }


    @Test(expected = ModelNotFoundException.class)
    public void shouldThrowException() {
       //Given:
        FavoriteDTO dto = new FavoriteDTO("9998823", false);

       //When:
        service.saveFavoriteRelation(USER_ID, dto);
    }

}