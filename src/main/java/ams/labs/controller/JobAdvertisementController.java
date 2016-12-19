package ams.labs.controller;

import ams.labs.entity.JobAdvertisement;
import ams.labs.entity.Location;
import ams.labs.service.JobAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "JobAdvertisement", description = "Track User Info")
@RestController
public class JobAdvertisementController {

    private final static Logger log = LoggerFactory.getLogger(JobAdvertisementController.class);

    @Autowired
    private JobAdvertisementService service;

    @ApiOperation(value = "Finds All users in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/jobs/all", method = RequestMethod.GET)
    public List<JobAdvertisement> viewAll() {

        List<JobAdvertisement> jobs = service.findAll();

        return jobs;
    }

    @ApiOperation(value = "Finds by title", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/jobs/id/{id}", method = RequestMethod.GET)
    public JobAdvertisement findByJobAdvertisementId(@PathVariable("id") String id) {

        JobAdvertisement jobAdvertisement = service.findByJobAdvertisementId(id);
        return jobAdvertisement;
    }

    @ApiOperation(value = "save", nickname = "save", produces = "application/json")
    @RequestMapping(value = "/jobs/save/{jobAdvertisementId}/location/{location}", method = RequestMethod.GET)
    public JobAdvertisement save(@PathVariable("jobAdvertisementId") String jobAdvertisementId, @PathVariable("location") String location) {

        JobAdvertisement job = service.findByJobAdvertisementId(jobAdvertisementId);
        if (job == null) {
            job = new JobAdvertisement();
            job.setJobAdvertisementId(jobAdvertisementId);
            job.setLocation(new Location(location));
        }

        service.save(job);

        return job;
    }

}
