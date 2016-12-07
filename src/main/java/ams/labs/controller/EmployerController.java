package ams.labs.controller;

import ams.labs.entity.Employer;
import ams.labs.service.EmployerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Employer Entity", description = "CRUD Methods for Employer Entity")
@RestController
public class EmployerController {

    @Autowired
    private EmployerService service;

    @ApiOperation(value = "Find by id", nickname = "Find by id", produces = "application/json")
    @RequestMapping(value = "/employer/{employerId}", method = RequestMethod.GET)
    public @ResponseBody Employer findEmployerById(@PathVariable("employerId") Long employerId) {
        return service.findByEmployerId(employerId);
    }

    @ApiOperation(value = "Save Employer Entity in db", nickname = "save", produces = "application/json")
    @RequestMapping(value = "/employer/{name}/{registrationNumber}/{employerId}", method = RequestMethod.POST)
    public @ResponseBody String save(@PathVariable("name") String name,
                                     @PathVariable("employerId") Long employerId,
                                     @PathVariable("registrationNumber") Long registrationNumber) {

        Employer employer = service.findByEmployerId(employerId);
        if (employer == null) {
            employer = new Employer();
            employer.setName(name);
            employer.setEmployerId(employerId);
            employer.setRegistrationNumber(registrationNumber);
        }

        service.save(employer);

        return "SAVED...";
    }

    @ApiOperation(value = "Find All Employer Entities in db", nickname = "find all", produces = "application/json")
    @RequestMapping(value = "/employer/findall", method = RequestMethod.GET)
    public @ResponseBody List<Employer> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Delete All Employer Entities in db", nickname = "delete all", produces = "application/json")
    @RequestMapping(value = "/employer/deleteall", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAll() {

        service.deleteAll();
        return "Deleted...";
    }
}
