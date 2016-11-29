package ams.labs.controller;

import ams.labs.model.User;
import ams.labs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Api(value = "Track User", description = "Track User Activites")
@RestController
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;


    @ApiOperation(value = "Finds All Users in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/user/findall", method = RequestMethod.GET)
    public @ResponseBody List<User> viewAll() {

        List<User> allUsers = service.findAll();

        return allUsers;
    }

    @ApiOperation(value = "Finds by id", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody User findUser(@PathVariable("id") String id) {
        User user = service.findByUserId(id);
        return user;
    }


    @ApiOperation(value = "Add", nickname = "Add", produces = "application/json")
    @RequestMapping(value = "/user/add/{id}", method = RequestMethod.POST)
    public @ResponseBody User addUser(@PathVariable("id") String id) {

        User user = new User();
        user.setUserId(id);

        service.save(user);

        return user;
    }

    @ApiOperation(value = "Delete All", nickname = "Delete All", produces = "application/json")
    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAll() {

        log.info("deleting all in db...");
        service.deleteAll();

        return "Deleted All...";
    }

}
