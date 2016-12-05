package ams.labs.controller;

import ams.labs.model.Profession;
import ams.labs.service.ProfessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Profession Entity", description = "Profession Entity CRUD")
@RestController
public class ProfessionController {

    @Autowired
    private ProfessionService service;

    @ApiOperation(value = "Save Profession Entity in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/profession/{id}/{name}", method = RequestMethod.POST)
    public
    @ResponseBody
    String save(@PathVariable("id") Long id, @PathVariable("name") String name) {

        Profession profession = service.findByProfessionId(id);
        if (profession == null) {
            profession = new Profession();
            profession.setProfessionId(id);
            profession.setName(name);
        }

        service.save(profession);


        return "SAVED...";
    }
}
