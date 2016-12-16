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

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Neo4jTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class JobAdvertisementRepositoryTest {

    private static final Long JOB_ADVERTISEMENT_ID = new Long(1000001);
    private static final Long JOB_ADVERTISEMENT_ID_2 = new Long(1000002);
    private static final Long JOB_ADVERTISEMENT_ID_3 = new Long(1000003);
    private static final String SYSTEMUTVECKLARE = "Systemutvecklare";
    private static final Long LOCATION_ID_STOCKHOLM = new Long(180);
    private static final Long PROFESSION_ID_SYSTEMUTVECKLARE = new Long(1234);
    private static final Long PROFESSION_ID_JURIST = new Long(4567);
    private static final String JURIST = "jurist";
    private static final Long LOCATION_ID_UPPSALA = new Long(181);
    private static final String STOCKHOLM = "Stockholm";
    private static final String UPPSALA = "Uppsala";
    private static final Long EMPLOYER_ID = new Long(20383691);
    private static final Long REGISTRATION_NUMBER = new Long(2021005646);
    private static final String KRONOFOGDEMYNDIGHETEN = "Kronofogdemyndigheten";


    private Location stockholm = new Location();
    private Location uppsala = new Location();
    private Profession systemutvecklare = new Profession();
    private Profession jurist = new Profession();

    private JobAdvertisement jobAd = new JobAdvertisement();
    private JobAdvertisement jobAd2 = new JobAdvertisement();
    private JobAdvertisement jobAd3 = new JobAdvertisement();


    @Autowired
    private JobAdvertisementRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerRepository employerRepository;


    @Before
    public void setUp() throws Exception {

        //Location
        stockholm.setName(STOCKHOLM);
        stockholm.setLocationId(LOCATION_ID_STOCKHOLM);
        locationRepository.save(stockholm);

        uppsala.setName(UPPSALA);
        uppsala.setLocationId(LOCATION_ID_UPPSALA);
        locationRepository.save(uppsala);

        //Profession
        systemutvecklare.setName(SYSTEMUTVECKLARE);
        systemutvecklare.setProfessionId(PROFESSION_ID_SYSTEMUTVECKLARE);
        professionRepository.save(systemutvecklare);

        jurist.setName(JURIST);
        jurist.setProfessionId(PROFESSION_ID_JURIST);
        professionRepository.save(jurist);

        //JobAds
        jobAd.setJobAdvertisementId(JOB_ADVERTISEMENT_ID);
        jobAd.setProfession(systemutvecklare);
        jobAd.setLocation(stockholm);
        repository.save(jobAd);

        jobAd2.setJobAdvertisementId(JOB_ADVERTISEMENT_ID_2);
        jobAd2.setProfession(systemutvecklare);
        jobAd2.setLocation(stockholm);
        repository.save(jobAd2);

        jobAd3.setJobAdvertisementId(JOB_ADVERTISEMENT_ID_3);
        jobAd3.setProfession(jurist);
        jobAd3.setLocation(uppsala);
        repository.save(jobAd3);

        User user = new User();
        user.lookedAt(jobAd);
        userRepository.save(user);

        User user2 = new User();
        user2.lookedAt(jobAd2);
        userRepository.save(user2);

        User user3 = new User();
        user3.lookedAt(jobAd);
        user3.lookedAt(jobAd2);
        user3.lookedAt(jobAd3);
        userRepository.save(user3);

        User user4 = new User();
        user4.lookedAt(jobAd);
        userRepository.save(user4);

        User user5 = new User();
        user5.lookedAt(jobAd);
        userRepository.save(user5);

        Employer employer = new Employer();
        employer.setEmployerId(EMPLOYER_ID);
        employer.setRegistrationNumber(REGISTRATION_NUMBER);
        employer.setName(KRONOFOGDEMYNDIGHETEN);

        employer.advertise(jobAd);
        employer.advertise(jobAd2);
        employer.advertise(jobAd3);
        employerRepository.save(employer);


    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
        locationRepository.deleteAll();
        professionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void findByJobAdvertisementId() throws Exception {
        //When:
        JobAdvertisement result = repository.findByJobAdvertisementId(JOB_ADVERTISEMENT_ID);

        //Then:
        assertThat(result).isNotNull();
        assertThat(result.getJobAdvertisementId()).isEqualTo(JOB_ADVERTISEMENT_ID);

        assertThat(result.getLocation().getLocationId()).isEqualTo(LOCATION_ID_STOCKHOLM);
        assertThat(result.getLocation().getName()).isEqualTo(STOCKHOLM);

        assertThat(result.getProfession().getProfessionId()).isEqualTo(PROFESSION_ID_SYSTEMUTVECKLARE);
        assertThat(result.getProfession().getName()).isEqualTo(SYSTEMUTVECKLARE);

    }

    @Test
    public void fetchMostWatchedJobAdvertisements() throws Exception {

        //When:
        List<JobAdsResult> jobIds = repository.fetchMostWatchedJobAdvertisements();

        JobAdsResult mostWatched = jobIds.get(0);
        JobAdsResult secondMostWatched = jobIds.get(1);
        JobAdsResult thirdMostWatched = jobIds.get(2);

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

        assertThat(jobAd.getJobAdvertisementId()).isEqualTo(mostWatched.getJobAdvertisementId());
        assertThat(jobAd2.getJobAdvertisementId()).isEqualTo(secondMostWatched.getJobAdvertisementId());
        assertThat(jobAd3.getJobAdvertisementId()).isEqualTo(thirdMostWatched.getJobAdvertisementId());
    }

    @Test
    public void fetchMostWatchedJobAdsForLocation() throws Exception {

        //When:
        List<JobAdsResult> result = repository.fetchMostWatchedJobAdsForLocation(LOCATION_ID_STOCKHOLM);

        //Then:
        assertThat(2).isEqualTo(result.size());
        assertThat(result.get(0).getViews()).isGreaterThan(result.get(1).getViews());

        assertThat(4).isEqualTo(result.get(0).getViews().intValue());
        assertThat(2).isEqualTo(result.get(1).getViews().intValue());

        assertThat(jobAd.getJobAdvertisementId()).isEqualTo(result.get(0).getJobAdvertisementId());
        assertThat(jobAd2.getJobAdvertisementId()).isEqualTo(result.get(1).getJobAdvertisementId());
    }

    @Test
    public void fetchMostWatchedJobAdsForProfession() throws Exception {

        //When:
        List<JobAdsResult> result = repository.fetchMostWatchedJobAdsForProfession(PROFESSION_ID_SYSTEMUTVECKLARE);

        //Then:
        assertThat(2).isEqualTo(result.size());
        assertThat(result.get(0).getViews()).isGreaterThan(result.get(1).getViews());

        assertThat(4).isEqualTo(result.get(0).getViews().intValue());
        assertThat(2).isEqualTo(result.get(1).getViews().intValue());

        assertThat(jobAd.getJobAdvertisementId()).isEqualTo(result.get(0).getJobAdvertisementId());
        assertThat(jobAd2.getJobAdvertisementId()).isEqualTo(result.get(1).getJobAdvertisementId());

    }

    @Test
    public void fetchMostWatchedJobAdsByEmployer() throws Exception {

        //When:
        List<JobAdsResult> result = repository.fetchMostWatchedJobAdsByEmployer(EMPLOYER_ID);

        JobAdsResult mostWatched = result.get(0);
        JobAdsResult secondMostWatched = result.get(1);
        JobAdsResult thirdMostWatched = result.get(2);

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

        assertThat(jobAd.getJobAdvertisementId()).isEqualTo(mostWatched.getJobAdvertisementId());
        assertThat(jobAd2.getJobAdvertisementId()).isEqualTo(secondMostWatched.getJobAdvertisementId());
        assertThat(jobAd3.getJobAdvertisementId()).isEqualTo(thirdMostWatched.getJobAdvertisementId());

    }

}