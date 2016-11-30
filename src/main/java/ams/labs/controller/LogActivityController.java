package ams.labs.controller;

import ams.labs.model.JobAdvertisement;
import ams.labs.model.User;
import ams.labs.service.JobAdvertisementService;
import ams.labs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "Log Activity", description = "Track User Activites")
@RestController
public class LogActivityController {

    private final static Logger log = LoggerFactory.getLogger(LogActivityController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JobAdvertisementService jobAdvertisementService;

    @ApiOperation(value = "mostWatcheds", nickname = "mostWatcheds", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviews/jobs", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop() {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdvertisements();

        return mostWatcheds;
    }

    @ApiOperation(value = "mostWatcheds for place", nickname = "mostWatcheds for place", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviews/place/{place}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ForPlace(@PathVariable("place") String place) {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsForPlace(place);

        return mostWatcheds;
    }

    @ApiOperation(value = "Finds by id", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/user/{userId}/watch/{jobId}", method = RequestMethod.GET)
    public @ResponseBody User logUser(@PathVariable("userId") String userId, @PathVariable("jobId") String jobId) {

        User user = userService.findByUserId(userId);
        if (user == null) {
            user = new User();
            user.setUserId(userId);
            userService.save(user);
        }

        JobAdvertisement job = jobAdvertisementService.findByJobAdvertisementId(jobId);
        if (job == null) {
            job = new JobAdvertisement();
            job.setJobAdvertisementId(jobId);
            jobAdvertisementService.save(job);
        }

        user.lookedAt(job);
        userService.save(user);

        return user;
    }


}
