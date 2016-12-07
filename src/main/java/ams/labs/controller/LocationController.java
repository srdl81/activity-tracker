package ams.labs.controller;

import ams.labs.entity.Location;
import ams.labs.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "Location Entity", description = "CRUD Methods for Location Entity")
@RestController
public class LocationController {

    @Autowired
    private LocationService service;

    @ApiOperation(value = "Save Location Entity in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/location/{id}/{name}", method = RequestMethod.POST)
    public @ResponseBody String save(@PathVariable("id") Long id, @PathVariable("name") String name) {

        Location location = service.findByLocationId(id);
        if (location == null) {
            location = new Location();
            location.setLocationId(id);
            location.setName(name);
        }

        service.save(location);


        return "SAVED...";
    }

    @ApiOperation(value = "Finds All Locations in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/location/findall", method = RequestMethod.GET)
    public @ResponseBody List<Location> viewAll() {

        List<Location> locations = service.findAll();

        return locations;
    }

    @ApiOperation(value = "Find by id", nickname = "Find by id", produces = "application/json")
    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    public @ResponseBody Location findLocationById(@PathVariable("id") Long id) {
        Location location = service.findByLocationId(id);
        return location;
    }

    @ApiOperation(value = "Delete All", nickname = "Delete All", produces = "application/json")
    @RequestMapping(value = "/location/delete", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAll() {

        service.deleteAll();

        return "Deleted All...";
    }


}
