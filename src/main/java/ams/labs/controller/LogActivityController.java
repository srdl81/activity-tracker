package ams.labs.controller;


import ams.labs.dto.MatchResultDTO;
import ams.labs.entity.*;
import ams.labs.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Log Activity", description = "Track User Activites")
@RestController
public class LogActivityController {

    private final static Logger log = LoggerFactory.getLogger(LogActivityController.class);

    @Autowired
    private ActivityService activityService;


    @ApiOperation(value = "Log User Activity", nickname = "Log Activity", produces = "application/json")
    @RequestMapping(value = "/user/{userId}/activity", method = RequestMethod.POST)
    public JobAdvertisement logUserActivity(@RequestBody MatchResultDTO matchResultDTO, @PathVariable("userId") Long userId) {

        log.info(String.format("logging activity for DTO '%s'", matchResultDTO));
        return activityService.logActivity(matchResultDTO, userId);
    }

}
