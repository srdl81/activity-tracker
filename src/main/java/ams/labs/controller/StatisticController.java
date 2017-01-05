package ams.labs.controller;

import ams.labs.service.JobAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Activity Statistics", description = "View Activity Statistics")
@RestController
public class StatisticController {

    @Autowired
    private JobAdvertisementService jobAdvertisementService;

    @ApiOperation(value = "Most viewed Job Ads total", nickname = "mostViewedJobs", produces = "application/json")
    @RequestMapping(value = "/most-viewed/jobs", method = RequestMethod.GET)
    public @ResponseBody List<String> getTop() {
        return jobAdvertisementService.fetchMostWatchedJobAdvertisements().stream()
                .map(s -> s.getJobAdvertisementId()).collect(Collectors.toList());
    }

    @ApiOperation(value = "Most viewed Job Ads for by specific location", nickname = "Job Ad for a location", produces = "application/json")
    @RequestMapping(value = "/most-viewed/jobs/location/{locationId}", method = RequestMethod.GET)
    public @ResponseBody List<String> getTop10ForLocation(@PathVariable("locationId") String locationId) {
        return jobAdvertisementService.fetchMostWatchedJobAdsForLocation(locationId).stream()
                .map(s -> s.getJobAdvertisementId()).collect(Collectors.toList());
    }

    @ApiOperation(value = "Most viewed Job Ads by specific profession", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/most-viewed/jobs/profession/{professionId}", method = RequestMethod.GET)
    public @ResponseBody List<String> getTop10ForYrkesNamn(@PathVariable("professionId") String professionId) {
        return jobAdvertisementService.fetchMostWatchedJobAdsByForProfession(professionId).stream()
                .map(s -> s.getJobAdvertisementId()).collect(Collectors.toList());
    }

    @ApiOperation(value = "Most viewed JobAds by specific Employer", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/most-viewed/jobs/employer/{employerId}", method = RequestMethod.GET)
    public @ResponseBody List<String> getTop10ByEmployer(@PathVariable("employerId") Long employerId) {
        return jobAdvertisementService.fetchMostWatchedJobAdsByEmployer(employerId).stream()
                .map(s -> s.getJobAdvertisementId()).collect(Collectors.toList());
    }
}
