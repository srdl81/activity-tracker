package ams.labs.controller;


import ams.labs.dto.AnnonsDTO;
import ams.labs.entity.Annons;
import ams.labs.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Api(value = "Log Activity", description = "Track User Activites")
@RestController
public class LogActivityController {

    private final static Logger log = LoggerFactory.getLogger(LogActivityController.class);
    public static final String URL = "https://www.arbetsformedlingen.se/rest/pbapi/af/v1/matchning/matchandeRekryteringsbehov/";

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "Log User Activity", nickname = "Log Activity", produces = "application/json")
    @RequestMapping(value = "/log/activity", method = RequestMethod.POST)
    public Annons logUserActivity(@Valid @RequestBody AnnonsDTO annonsDTO,
                                  @RequestParam(value = "user", defaultValue = "1001") Long user) {

        log.info(String.format("logging activity for user '%s' with DTO '%s'", user, annonsDTO));
        return logActivity(user, annonsDTO);
    }

    /*
        TEST METHOD
    */
    @ApiOperation(value = "TEST Generate activity FOR TEST", nickname = " TEST Log Activity", produces = "application/json")
    @RequestMapping(value = "/log/activity/generate", method = RequestMethod.POST)
    public String logUserActivity(@RequestParam("userId") Long userId, @RequestParam("id") String id) {

        ResponseEntity<AnnonsDTO> responseEntity = restTemplate.postForEntity(URL + id, getMultiValueMapHttpEntity(), AnnonsDTO.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            activityService.logActivity(responseEntity.getBody(), userId);
        }


        return "SUCCESS";
    }

    private Annons logActivity(@PathVariable("userId") Long userId, AnnonsDTO annonsDTO) {
        return activityService.logActivity(annonsDTO, userId);
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        return new HttpEntity<>(map, headers);
    }

}
