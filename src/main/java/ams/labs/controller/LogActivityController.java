package ams.labs.controller;

import ams.labs.model.JobAdvertisement;
import ams.labs.model.Location;
import ams.labs.model.Profession;
import ams.labs.model.User;
import ams.labs.service.JobAdvertisementService;
import ams.labs.service.LocationService;
import ams.labs.service.ProfessionService;
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

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProfessionService professionService;


    @ApiOperation(value = "mostWatcheds", nickname = "mostWatcheds", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviews/jobs", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop() {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdvertisements();

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed Job Ad for a location", nickname = "Job Ad for a location", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviews/location/{locationId}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ForLocation(@PathVariable("locationId") Long locationId) {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsForLocation(locationId);

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed Job Ad For a profession", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviews/yrkesid/{yrkesid}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ForYrkesNamn(@PathVariable("yrkesid") Long professionId) {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsByForProfession(professionId);

        return mostWatcheds;
    }

    @ApiOperation(value = "Finds by id", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/user/{userId}/looksat/job/{jobId}/in/{locationId}/y/{professionId}", method = RequestMethod.GET)
    public @ResponseBody User logUser(@PathVariable("userId") Long userId,
                                      @PathVariable("jobId") Long jobId,
                                      @PathVariable("locationId") Long locationId,
                                      @PathVariable("professionId") Long professionId) {

        User user = userService.findByUserId(userId);
        if (user == null) {
            user = new User();
            user.setUserId(userId);
            userService.save(user);
        }

        Profession profession = professionService.findByProfessionId(professionId);
        if (profession == null) {
            profession = new Profession();
            profession.setProfessionId(professionId);
            professionService.save(profession);
        }

        Location location = locationService.findByLocationId(locationId);
        if (location == null) {
            location = new Location();
            location.setLocationId(locationId);
            locationService.save(location);
        }

        JobAdvertisement job = jobAdvertisementService.findByJobAdvertisementId(jobId);
        if (job == null) {
            job = new JobAdvertisement();
            job.setJobAdvertisementId(jobId);
            job.setLocation(location);
            job.setProfession(profession);
            jobAdvertisementService.save(job);
        }

        user.lookedAt(job);
        userService.save(user);

        return user;
    }


}
