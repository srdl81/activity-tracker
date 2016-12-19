package ams.labs.controller;

import ams.labs.entity.JobAdsResult;
import ams.labs.repository.JobAdvertisementRepository;
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
    @RequestMapping(value = "/statistics/jobs/mostviews", method = RequestMethod.GET)
    public @ResponseBody List<JobAdsResult> getTop() {
        List<JobAdsResult> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdvertisements();

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed Job Ads for by specific location", nickname = "Job Ad for a location", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviews/location/{locationId}", method = RequestMethod.GET)
    public @ResponseBody List<JobAdsResult> getTop10ForLocation(@PathVariable("locationId") String locationId) {
        List<JobAdsResult> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsForLocation(locationId);

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed Job Ads by specific profession", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviews/profession/{professionId}", method = RequestMethod.GET)
    public @ResponseBody List<JobAdsResult> getTop10ForYrkesNamn(@PathVariable("professionId") String professionId) {
        List<JobAdsResult> mostWatcheds = jobAdvertisementService.fetchMostWatchedJobAdsByForProfession(professionId);

        return mostWatcheds;
    }

    @ApiOperation(value = "Most viewed JobAds by specific Employer", nickname = "Job Ad for a profession", produces = "application/json")
    @RequestMapping(value = "/statistics/mostviews/employer/{employerId}", method = RequestMethod.GET)
    public @ResponseBody List<JobAdsResult> getTop10ByEmployer(@PathVariable("employerId") Long employerId) {
        List<JobAdsResult> mostWatched = jobAdvertisementService.fetchMostWatchedJobAdsByEmployer(employerId);

        return mostWatched;
    }
}
