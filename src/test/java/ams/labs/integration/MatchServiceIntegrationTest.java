package ams.labs.integration;

import ams.labs.controller.LogActivityController;
import ams.labs.dto.AnnonsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchServiceIntegrationTest {

    private static final String ARBETSGIVAR_ID = "20315022";
    private static final String ARBETSGIVAR_NAMN = "Svea Vaccin AB";
    private static final String ORGANISATIONS_NUMMER = "5564548849";
    private static final String YRKES_ID = "7296";
    private static final String YRKES_NAMN = "Sjuksköterska, grundutbildad";
    private static final String KOMMUN_ID = "1280";
    private static final String KOMMUN_NAMN = "Malmö";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void checkMatchServiceData() {
        //Given:
        String id = "22038213";
        String url = LogActivityController.URL + id;

       //When:
        ResponseEntity<AnnonsDTO> responseEntity = restTemplate.postForEntity(url, getMultiValueMapHttpEntity(), AnnonsDTO.class);

       //Then:
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        AnnonsDTO body = responseEntity.getBody();

        assertThat(body)
                .isNotNull();

        assertThat(body.getId())
                .isNotNull()
                .isEqualTo(id);

        assertThat(body.getArbetsgivareId())
                .isEqualTo(null);

        assertThat(body.getOrganisationsnummer())
                .isNotNull()
                .isEqualTo(ORGANISATIONS_NUMMER);

        assertThat(body.getArbetsgivarenamn())
                .isNotNull()
                .isEqualTo(ARBETSGIVAR_NAMN);

        assertThat(body.getYrkesroll())
                .isNotNull();

        assertThat(body.getYrkesroll().getId())
                .isNotNull()
                .isEqualTo(YRKES_ID);

        assertThat(body.getYrkesroll().getNamn())
                .isNotNull()
                .isEqualTo(YRKES_NAMN);

        assertThat(body.getErbjudenArbetsplats())
                .isNotNull();

        assertThat(body.getErbjudenArbetsplats().getKommun())
                .isNotNull();

        assertThat(body.getErbjudenArbetsplats().getKommun().getId())
                .isNotNull()
                .isEqualTo(KOMMUN_ID);

        assertThat(body.getErbjudenArbetsplats().getKommun().getNamn())
                .isNotNull()
                .isEqualTo(KOMMUN_NAMN);
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        return new HttpEntity<>(map, headers);
    }

}
