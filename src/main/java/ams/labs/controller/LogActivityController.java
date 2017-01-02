package ams.labs.controller;


import ams.labs.dto.MatchResultDTO;
import ams.labs.entity.*;
import ams.labs.service.*;
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
import java.util.Arrays;

@Api(value = "Log Activity", description = "Track User Activites")
@RestController
public class LogActivityController {

    private final static Logger log = LoggerFactory.getLogger(LogActivityController.class);
    public static final String URL = "http://pilot.arbetsformedlingen.se:80/pbv3api/rest/matchning/v1/matchandeRekryteringsbehov/";

    @Autowired
    private ActivityService activityService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "Log User Activity", nickname = "Log Activity", produces = "application/json")
    @RequestMapping(value = "/user/{userId}/activity", method = RequestMethod.POST)
    public JobAdvertisement logUserActivity(@Valid @RequestBody MatchResultDTO matchResultDTO,
                                            @PathVariable("userId") Long userId) {

        log.info(String.format("logging activity for DTO '%s'", matchResultDTO));
        return logActivity(userId, matchResultDTO);
    }

    @ApiOperation(value = "Generate activity", nickname = "Log Activity", produces = "application/json")
    @RequestMapping(value = "/user/activity", method = RequestMethod.POST)
    public String logUserActivity(@RequestParam("userId") Long userId, @RequestParam("jobIds") String jobIds) {

        for (String id : Arrays.asList(jobIds.split(","))) {
            ResponseEntity<MatchResultDTO> responseEntity = restTemplate.postForEntity(URL + id, getMultiValueMapHttpEntity(), MatchResultDTO.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                activityService.logActivity(responseEntity.getBody(), userId);
            }
        }

        return "SUCCESS";
    }

    private JobAdvertisement logActivity(@PathVariable("userId") Long userId, MatchResultDTO matchResultDTO) {
        return activityService.logActivity(matchResultDTO, userId);
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        return new HttpEntity<>(map, headers);
    }

}
