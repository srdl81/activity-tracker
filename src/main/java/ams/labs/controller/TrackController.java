package ams.labs.controller;

import ams.labs.Application;
import ams.labs.model.Person;
import ams.labs.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Api(value = "Track User", description = "Track User Activites")
@RestController
public class TrackController {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TrackService trackService;


    @ApiOperation(value = "Add", nickname = "Add", produces = "application/json")
    @RequestMapping(value = "/add/{name}", method = RequestMethod.POST)
    public @ResponseBody String addUser(@PathVariable("name") String name) {

        log.info(String.format("trying to add '%s' to db", name));

        Person person = new Person(name);
        trackService.save(person);

        return String.format("added user: %s", name);
    }

    @ApiOperation(value = "Delete specific user", nickname = "Delete specific", produces = "application/json")
    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteSpecificUser(@PathVariable("name") String name) {

        log.info(String.format("deleting %s in db...", name));
        trackService.deletePerson(name);

        return String.format("person with name %s is deleted", name);
    }

    @ApiOperation(value = "Delete All", nickname = "Delete All", produces = "application/json")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAll() {

        log.info("deleting all in db...");
        trackService.deleteAll();

        return "Deleted All...";
    }

    @ApiOperation(value = "Finds All persons in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public @ResponseBody List<Person> viewAll() {

        log.info("finds all persons in db...");
        List<Person> allPersons = trackService.findAll();


        return allPersons;
    }

    @ApiOperation(value = "TrackUser", nickname = "TrackUser", produces = "application/json")
    @RequestMapping(value = "/track", method = RequestMethod.GET)
    public String getHelloMessage() {

        log.info("deleting all in db...");
        trackService.deleteAll();

        Person greg = new Person("Greg");
        Person roy = new Person("Roy");
        Person craig = new Person("Craig");

        List<Person> team = Arrays.asList(greg, roy, craig);

        log.info("Before linking up with Neo4j...");

        team.stream()
                .forEach(person -> log.info("\t" + person.toString()));

        trackService.save(greg);
        trackService.save(roy);
        trackService.save(craig);

        greg = trackService.findByName(greg.getName());
        greg.worksWith(roy);
        greg.worksWith(craig);
        trackService.save(greg);

        roy = trackService.findByName(roy.getName());
        roy.worksWith(craig);
        // We already know that roy works with greg
        trackService.save(roy);

        // We already know craig works with roy and greg

        log.info("Lookup each person by name...");
        team.stream().forEach(person -> log.info("\t" + trackService.findByName(person.getName()).toString()));

        return "NBA ACTION IS VERSION";
    }

}
