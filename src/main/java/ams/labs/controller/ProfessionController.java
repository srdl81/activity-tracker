package ams.labs.controller;

import ams.labs.entity.Profession;
import ams.labs.service.ProfessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Profession Entity", description = "CRUD Methods for Profession Entity")
@RestController
public class ProfessionController {

    @Autowired
    private ProfessionService service;

    @ApiOperation(value = "Save Profession Entity in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/profession/{id}/{name}", method = RequestMethod.POST)
    public @ResponseBody String save(@PathVariable("id") Long id, @PathVariable("name") String name) {

        Profession profession = service.findByProfessionId(id);
        if (profession == null) {
            profession = new Profession();
            profession.setProfessionId(id);
            profession.setName(name);
        }

        service.save(profession);

        return "SAVED...";
    }

    @ApiOperation(value = "Finds All Professions in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/profession/findall", method = RequestMethod.GET)
    public @ResponseBody List<Profession> viewAll() {

        List<Profession> professions = service.findAll();

        return professions;
    }

    @ApiOperation(value = "Find by id", nickname = "Find by id", produces = "application/json")
    @RequestMapping(value = "/profession/{id}", method = RequestMethod.GET)
    public @ResponseBody Profession findProfessionById(@PathVariable("id") Long id) {
        Profession profession = service.findByProfessionId(id);
        return profession;
    }

    @ApiOperation(value = "Delete All", nickname = "Delete All", produces = "application/json")
    @RequestMapping(value = "/profession/delete", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAll() {

        service.deleteAll();

        return "Deleted All...";
    }
}
