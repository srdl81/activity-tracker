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
