package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static ams.labs.util.GeneralUtil.getCurrentDate;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class AnnonsRepositoryTest {

    private static final String JOB_ADVERTISEMENT_ID = "6968821";
    private static final String JOB_ADVERTISEMENT_ID_2 = "6968822";
    private static final String JOB_ADVERTISEMENT_ID_3 = "6968823";
    private static final String SYSTEMUTVECKLARE = "Systemutvecklare";
    private static final String LOCATION_ID_STOCKHOLM = "0180";
    private static final String LOCATION_ID_UPPSALA = "0181";
    private static final String YRKE_ID_SYSTEMUTVECKLARE = "1234";
    private static final String YRKE_ID_JURIST = "4567";
    private static final String JURIST = "jurist";

    private static final String STOCKHOLM = "Stockholm";
    private static final String UPPSALA = "Uppsala";
    private static final Long EMPLOYER_ID = new Long(20383691);
    private static final Long REGISTRATION_NUMBER = new Long(2021005646);
    private static final String KRONOFOGDEMYNDIGHETEN = "Kronofogdemyndigheten";


    private Plats stockholm = new Plats();
    private Plats uppsala = new Plats();
    private Yrke systemutvecklare = new Yrke();
    private Yrke jurist = new Yrke();

    private Annons jobAd = new Annons();
    private Annons jobAd2 = new Annons();
    private Annons jobAd3 = new Annons();


    @Autowired
    private AnnonsRepository repository;

    @Autowired
    private PlatsRepository platsRepository;

    @Autowired
    private YrkeRepository yrkeRepository;

    @Autowired
    private AnvardarRepository anvardarRepository;

    @Autowired
    private ArbetsgivarRepository arbetsgivarRepository;

    @Autowired
    private TittatRepository tittatRepository;


    @Before
    public void setUp() throws Exception {

        //Location
        stockholm.setNamn(STOCKHOLM);
        stockholm.setPlatsId(LOCATION_ID_STOCKHOLM);
        platsRepository.save(stockholm);

        uppsala.setNamn(UPPSALA);
        uppsala.setPlatsId(LOCATION_ID_UPPSALA);
        platsRepository.save(uppsala);

        //Profession
        systemutvecklare.setName(SYSTEMUTVECKLARE);
        systemutvecklare.setYrkeId(YRKE_ID_SYSTEMUTVECKLARE);
        yrkeRepository.save(systemutvecklare);

        jurist.setName(JURIST);
        jurist.setYrkeId(YRKE_ID_JURIST);
        yrkeRepository.save(jurist);

        //JobAds
        jobAd.setAnnonsId(JOB_ADVERTISEMENT_ID);
        jobAd.setYrke(systemutvecklare);
        jobAd.setPlats(stockholm);
        repository.save(jobAd);

        jobAd2.setAnnonsId(JOB_ADVERTISEMENT_ID_2);
        jobAd2.setYrke(systemutvecklare);
        jobAd2.setPlats(stockholm);
        repository.save(jobAd2);

        jobAd3.setAnnonsId(JOB_ADVERTISEMENT_ID_3);
        jobAd3.setYrke(jurist);
        jobAd3.setPlats(uppsala);
        repository.save(jobAd3);

        tittatRepository.save(new Tittat(new Anvandare(), jobAd, getCurrentDate()));
        tittatRepository.save(new Tittat(new Anvandare(), jobAd2, getCurrentDate()));

        tittatRepository.save(new Tittat(new Anvandare(), jobAd, getCurrentDate()));
        tittatRepository.save(new Tittat(new Anvandare(), jobAd2, getCurrentDate()));
        tittatRepository.save(new Tittat(new Anvandare(), jobAd3, getCurrentDate()));
        tittatRepository.save(new Tittat(new Anvandare(), jobAd, getCurrentDate()));
        tittatRepository.save(new Tittat(new Anvandare(), jobAd, getCurrentDate()));

        Arbetsgivare arbetsgivare = new Arbetsgivare();
        arbetsgivare.setArbetsgivarId(EMPLOYER_ID);
        arbetsgivare.setOrganisationsnummer(REGISTRATION_NUMBER);
        arbetsgivare.setNamn(KRONOFOGDEMYNDIGHETEN);

        arbetsgivare.advertise(jobAd);
        arbetsgivare.advertise(jobAd2);
        arbetsgivare.advertise(jobAd3);
        arbetsgivarRepository.save(arbetsgivare);


    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
        platsRepository.deleteAll();
        yrkeRepository.deleteAll();
        anvardarRepository.deleteAll();
    }

    @Test
    public void findByJobAdvertisementId() throws Exception {
        //When:
        Annons result = repository.findByAnnonsId(JOB_ADVERTISEMENT_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getAnnonsId()).isEqualTo(JOB_ADVERTISEMENT_ID);

        assertThat(result.getPlats().getPlatsId()).isEqualTo(LOCATION_ID_STOCKHOLM);
        assertThat(result.getPlats().getNamn()).isEqualTo(STOCKHOLM);

        assertThat(result.getYrke().getYrkeId()).isEqualTo(YRKE_ID_SYSTEMUTVECKLARE);
        assertThat(result.getYrke().getName()).isEqualTo(SYSTEMUTVECKLARE);

    }

    @Test
    public void fetchMostWatchedJobAdvertisements() throws Exception {

        //When:
        List<AnnonsResult> jobIds = repository.fetchMostWatchedAnnonser();

        AnnonsResult mostWatched = jobIds.get(0);
        AnnonsResult secondMostWatched = jobIds.get(1);
        AnnonsResult thirdMostWatched = jobIds.get(2);

        //Then:
        assertThat(mostWatched.getViews())
                .isGreaterThan(secondMostWatched.getViews())
                .isGreaterThan(thirdMostWatched.getViews());

        assertThat(secondMostWatched.getViews())
                .isGreaterThan(thirdMostWatched.getViews())
                .isLessThan(mostWatched.getViews());

        assertThat(thirdMostWatched.getViews())
                .isLessThan(secondMostWatched.getViews())
                .isLessThan(mostWatched.getViews());

        assertThat(4).isEqualTo(mostWatched.getViews().intValue());
        assertThat(2).isEqualTo(secondMostWatched.getViews().intValue());
        assertThat(1).isEqualTo(thirdMostWatched.getViews().intValue());

        assertThat(jobAd.getAnnonsId()).isEqualTo(mostWatched.getAnnonsId());
        assertThat(jobAd2.getAnnonsId()).isEqualTo(secondMostWatched.getAnnonsId());
        assertThat(jobAd3.getAnnonsId()).isEqualTo(thirdMostWatched.getAnnonsId());
    }

    @Test
    public void fetchMostWatchedJobAdsForLocation() throws Exception {

        //When:
        List<AnnonsResult> result = repository.fetchMostWatchedJobAdsByPlats(LOCATION_ID_STOCKHOLM);

        //Then:
        assertThat(2).isEqualTo(result.size());
        assertThat(result.get(0).getViews()).isGreaterThan(result.get(1).getViews());

        assertThat(4).isEqualTo(result.get(0).getViews().intValue());
        assertThat(2).isEqualTo(result.get(1).getViews().intValue());

        assertThat(jobAd.getAnnonsId()).isEqualTo(result.get(0).getAnnonsId());
        assertThat(jobAd2.getAnnonsId()).isEqualTo(result.get(1).getAnnonsId());
    }

    @Test
    public void fetchMostWatchedJobAdsByYrke() throws Exception {

        //When:
        List<AnnonsResult> result = repository.fetchMostWatchedJobAdsByYrke(YRKE_ID_SYSTEMUTVECKLARE);

        //Then:
        assertThat(2).isEqualTo(result.size());
        assertThat(result.get(0).getViews()).isGreaterThan(result.get(1).getViews());

        assertThat(4).isEqualTo(result.get(0).getViews().intValue());
        assertThat(2).isEqualTo(result.get(1).getViews().intValue());

        assertThat(jobAd.getAnnonsId()).isEqualTo(result.get(0).getAnnonsId());
        assertThat(jobAd2.getAnnonsId()).isEqualTo(result.get(1).getAnnonsId());

    }

    @Test
    public void fetchMostWatchedJobAdsByEmployer() throws Exception {

        //When:
        List<AnnonsResult> result = repository.fetchMostWatchedJobAdsByArbetsgivare(EMPLOYER_ID);

        AnnonsResult mostWatched = result.get(0);
        AnnonsResult secondMostWatched = result.get(1);
        AnnonsResult thirdMostWatched = result.get(2);

        //Then:
        assertThat(mostWatched.getViews())
                .isGreaterThan(secondMostWatched.getViews())
                .isGreaterThan(thirdMostWatched.getViews());

        assertThat(secondMostWatched.getViews())
                .isGreaterThan(thirdMostWatched.getViews())
                .isLessThan(mostWatched.getViews());

        assertThat(thirdMostWatched.getViews())
                .isLessThan(secondMostWatched.getViews())
                .isLessThan(mostWatched.getViews());

        assertThat(4).isEqualTo(mostWatched.getViews().intValue());
        assertThat(2).isEqualTo(secondMostWatched.getViews().intValue());
        assertThat(1).isEqualTo(thirdMostWatched.getViews().intValue());

        assertThat(jobAd.getAnnonsId()).isEqualTo(mostWatched.getAnnonsId());
        assertThat(jobAd2.getAnnonsId()).isEqualTo(secondMostWatched.getAnnonsId());
        assertThat(jobAd3.getAnnonsId()).isEqualTo(thirdMostWatched.getAnnonsId());

    }

}