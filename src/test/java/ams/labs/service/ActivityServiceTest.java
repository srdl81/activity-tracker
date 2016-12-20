package ams.labs.service;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.dto.ErbjudenArbetsplats;
import ams.labs.dto.IdNamn;
import ams.labs.dto.MatchResultDTO;
import ams.labs.entity.User;
import ams.labs.repository.UserRepository;
import org.junit.Before;
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
public class ActivityServiceTest {

    private static final Long USER_ID = new Long(111111001);

    @Autowired
    private ActivityService service;

    @Autowired
    private UserRepository userRepository;

    private MatchResultDTO resultDTO = new MatchResultDTO();


    @Before
    public void setUp() throws Exception {
        resultDTO.setId("6968823");
        resultDTO.setArbetsgivareId("10820089");
        resultDTO.setArbetsgivarenamn("Landstinget Dalarna");
        resultDTO.setOrganisationsnummer("2321000180");

        ErbjudenArbetsplats erbjudenArbetsplats = new ErbjudenArbetsplats();

        IdNamn kommun = new IdNamn();
        kommun.setId("0180");
        kommun.setNamn("Stockholm");
        erbjudenArbetsplats.setKommun(kommun);
        resultDTO.setErbjudenArbetsplats(erbjudenArbetsplats);

        IdNamn yrkesRoll = new IdNamn();
        yrkesRoll.setId("7296");
        yrkesRoll.setNamn("Sjuksköterska, grundutbildad");
        resultDTO.setYrkesroll(yrkesRoll);

    }

    @Test
    public void logActivity() throws Exception {
        service.logActivity(resultDTO, USER_ID);

        User user = userRepository.findByUserId(USER_ID);

        assertThat(user).isNotNull();

        assertThat(user.getWatched())
                .isNotNull()
                .isNotEmpty();

        assertThat(user.getWatched().size()).isEqualTo(1);
    }

    @Test
    public void logActivityAndMakeSureThatItOnlyWillPersistOne() throws Exception {
        service.logActivity(resultDTO, USER_ID);
        service.logActivity(resultDTO, USER_ID);

        User user = userRepository.findByUserId(USER_ID);

        assertThat(user).isNotNull();

        assertThat(user.getWatched())
                .isNotNull()
                .isNotEmpty();

        assertThat(user.getWatched().size()).isEqualTo(1);
    }

}