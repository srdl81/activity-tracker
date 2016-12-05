package ams.labs.controller;

import ams.labs.model.Location;
import ams.labs.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Api(value = "Location Entity", description = "Location Entity CRUD")
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


}
