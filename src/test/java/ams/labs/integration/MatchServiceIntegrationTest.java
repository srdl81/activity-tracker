package ams.labs.integration;

import ams.labs.dto.MatchResultDTO;
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

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchServiceIntegrationTest {

    private static final String ARBETSGIVAR_ID = "20315022";
    private static final String ARBETSGIVAR_NAMN = "Magna AB";
    private static final String ORGANISATIONS_NUMMER = "5565636247";
    private static final String YRKES_ID = "2603";
    private static final String YRKES_NAMN = "Innesäljare";
    private static final String KOMMUN_ID = "0180";
    private static final String KOMMUN_NAMN = "Stockholm";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void checkMatchServiceData() throws IOException {
        //Given:
        String URL = "https://www.arbetsformedlingen.se/rest/matchning/rest/matchning/v1/matchandeRekryteringsbehov/";
        String id = "6968822";

       //When:
        ResponseEntity<MatchResultDTO> responseEntity = restTemplate.postForEntity(URL + id, getMultiValueMapHttpEntity(), MatchResultDTO.class);


       //Then:
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        MatchResultDTO body = responseEntity.getBody();

        assertThat(body)
                .isNotNull();

        assertThat(body.getId())
                .isNotNull()
                .isEqualTo(id);

        assertThat(body.getArbetsgivareId())
                .isNotNull()
                .isEqualTo(ARBETSGIVAR_ID);

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
