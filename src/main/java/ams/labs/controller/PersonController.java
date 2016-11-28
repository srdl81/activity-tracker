package ams.labs.controller;

import ams.labs.model.Person;
import ams.labs.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Api(value = "Track User", description = "Track User Activites")
@RestController
public class PersonController {

    private final static Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;


    @ApiOperation(value = "Finds All persons in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/person/findall", method = RequestMethod.GET)
    public @ResponseBody List<Person> viewAll() {

        log.info("finds all persons in db...");
        List<Person> allPersons = personService.findAll();

        return allPersons;
    }

    @ApiOperation(value = "Finds by name", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/person/{name}", method = RequestMethod.GET)
    public @ResponseBody Person findPerson(@PathVariable("name") String name) {
        Person person = personService.findByName(name);
        return person;
    }

    @ApiOperation(value = "Add", nickname = "Add", produces = "application/json")
    @RequestMapping(value = "/person/add/{name}", method = RequestMethod.POST)
    public @ResponseBody String addUser(@PathVariable("name") String name) {

        Person person = new Person(name);
        personService.save(person);

        return String.format("added user: %s", name);
    }

    @ApiOperation(value = "Delete All", nickname = "Delete All", produces = "application/json")
    @RequestMapping(value = "/person/delete", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAll() {

        log.info("deleting all in db...");
        personService.deleteAll();

        return "Deleted All...";
    }

}
