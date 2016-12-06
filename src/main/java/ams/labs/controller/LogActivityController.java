package ams.labs.controller;

import ams.labs.model.*;
import ams.labs.service.*;
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

    @Autowired
    private EmployerService employerService;


    @ApiOperation(value = "mostWatcheds", nickname = "mostViewedJobs", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviewed/jobs", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop() {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdvertisements();

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed Job Ad for a location", nickname = "Job Ad for a location", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviewed/location/{locationId}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ForLocation(@PathVariable("locationId") Long locationId) {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsForLocation(locationId);

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed Job Ad For a profession", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviewed/yrkesid/{yrkesid}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ForYrkesNamn(@PathVariable("yrkesid") Long professionId) {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsByForProfession(professionId);

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed JobAds By Employer", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviewed/employer/{employerId}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ByEmployer(@PathVariable("employerId") Long employerId) {
        List<Map<String, Object>> mostWatched = jobAdvertisementService.fetchMostWatchedJobAdsByEmployer(employerId);

        return mostWatched;
    }

    @ApiOperation(value = "Finds by id", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/user/{userId}/looksat/job/{jobId}/in/{locationId}/y/{professionId}/{employerId}", method = RequestMethod.GET)
    public @ResponseBody User logUser(@PathVariable("userId") Long userId,
                                      @PathVariable("jobId") Long jobId,
                                      @PathVariable("locationId") Long locationId,
                                      @PathVariable("professionId") Long professionId,
                                      @PathVariable("employerId") Long employerId) {

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

        Employer employer = employerService.findByEmployerId(employerId);
        if (employer == null) {
            employer = new Employer();
            employer.setEmployerId(employerId);
            employerService.save(employer);
        }

        employer.advertise(job);
        employerService.save(employer);

        return user;
    }


}
