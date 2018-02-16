package ams.labs.service;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.dto.ErbjudenArbetsplatsDTO;
import ams.labs.dto.PropertyDTO;
import ams.labs.dto.AnnonsDTO;
import ams.labs.entity.Anvandare;
import ams.labs.repository.AnvardarRepository;
import org.junit.Before;
import org.junit.Ignore;
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
@Ignore
public class ActivityServiceTest {

    private static final Long USER_ID = new Long(111111001);

    @Autowired
    private ActivityService service;

    @Autowired
    private AnvardarRepository anvardarRepository;

    private AnnonsDTO annonsDTO = new AnnonsDTO();


    @Before
    public void setUp() throws Exception {
        annonsDTO.setId("6968823");
        annonsDTO.setArbetsgivarenamn("Landstinget Dalarna");
        annonsDTO.setOrganisationsnummer("2321000180");

        ErbjudenArbetsplatsDTO erbjudenArbetsplats = new ErbjudenArbetsplatsDTO();

        PropertyDTO kommun = new PropertyDTO();
        kommun.setId("0180");
        kommun.setNamn("Stockholm");
        erbjudenArbetsplats.setKommun(kommun);
        annonsDTO.setErbjudenArbetsplats(erbjudenArbetsplats);

        PropertyDTO yrkesRoll = new PropertyDTO();
        yrkesRoll.setId("7296");
        yrkesRoll.setNamn("Sjuksköterska, grundutbildad");
        annonsDTO.setYrkesroll(yrkesRoll);

    }

    @Test
    public void logActivity() {
        service.logActivity(annonsDTO, USER_ID);

        Anvandare anvandare = anvardarRepository.findByAnvandarId(USER_ID);

        assertThat(anvandare).isNotNull();

        assertThat(anvandare.getTittat())
                .isNotNull()
                .isNotEmpty();

        assertThat(anvandare.getTittat().size()).isEqualTo(1);
    }

    @Test
    public void logActivityAndMakeSureThatItOnlyWillPersistOne() {
        service.logActivity(annonsDTO, USER_ID);
        service.logActivity(annonsDTO, USER_ID);

        Anvandare anvandare = anvardarRepository.findByAnvandarId(USER_ID);

        assertThat(anvandare).isNotNull();

        assertThat(anvandare.getTittat())
                .isNotNull()
                .isNotEmpty();

        assertThat(anvandare.getTittat().size()).isEqualTo(1);
    }

}