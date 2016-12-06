package ams.labs.controller;

import ams.labs.service.JobAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "Activity Statistics", description = "View Activity Statistics")
@RestController
public class StatisticController {

    @Autowired
    private JobAdvertisementService jobAdvertisementService;


    @ApiOperation(value = "Most viewed Job Ads total", nickname = "mostViewedJobs", produces = "application/json")
    @RequestMapping(value = "/statistics/jobs/mostviewed", method = RequestMethod.GET)
    public @ResponseBody
    List<Map<String, Object>> getTop() {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdvertisements();

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed Job Ads for by specific location", nickname = "Job Ad for a location", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviewed/location/{locationId}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ForLocation(@PathVariable("locationId") Long locationId) {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsForLocation(locationId);

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed Job Ads by specific profession", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviewed/profession/{professionId}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ForYrkesNamn(@PathVariable("professionId") Long professionId) {
        List<Map<String, Object>> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsByForProfession(professionId);

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed JobAds by specific Employer", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviewed/employer/{employerId}", method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getTop10ByEmployer(@PathVariable("employerId") Long employerId) {
        List<Map<String, Object>> mostWatched = jobAdvertisementService.fetchMostWatchedJobAdsByEmployer(employerId);

        return mostWatched;
    }
}
