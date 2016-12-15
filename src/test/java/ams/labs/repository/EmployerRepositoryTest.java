package ams.labs.repository;

import ams.labs.configuration.Neo4jTestConfiguration;
import ams.labs.entity.Employer;
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
public class EmployerRepositoryTest {

    private static final Long AMS_EMPLOYER_ID = new Long(20383691);
    private static final Long AMS_REG_NR = new Long(2021005646);
    private static final String AMS_NAME = "Kronofogdemyndigheten";

    @Autowired
    private EmployerRepository repository;

    @Test
    public void findByEmployerId() throws Exception {
        //Given:
        Employer employer = new Employer();
        employer.setName(AMS_NAME);
        employer.setRegistrationNumber(AMS_REG_NR);
        employer.setEmployerId(AMS_EMPLOYER_ID);
        repository.save(employer);

        //When:
        Employer result = repository.findByEmployerId(AMS_EMPLOYER_ID);

        //Then:
        assertNotNull(result);
        assertEquals(employer, result);
    }

}