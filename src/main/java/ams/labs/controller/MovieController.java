package ams.labs.controller;

import ams.labs.model.Movie;
import ams.labs.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Movie", description = "Track User Movie")
@RestController
public class MovieController {

    private final static Logger log = LoggerFactory.getLogger(MovieController.class);


    @Autowired
    private MovieService service;


    @ApiOperation(value = "Finds All persons in db", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/movie/all", method = RequestMethod.GET)
    public List<Movie> viewAll() {

        log.info("finds all persons in db...");
        List<Movie> movies = service.findAll();

        return movies;
    }

    @ApiOperation(value = "Finds by title", nickname = "Find All", produces = "application/json")
    @RequestMapping(value = "/movie/title/{title}", method = RequestMethod.GET)
    public Movie findByTitle(@PathVariable("title") String title) {

        Movie movie = service.findByTitle(title);
        return movie;
    }

    @ApiOperation(value = "save", nickname = "save", produces = "application/json")
    @RequestMapping(value = "/movie/save/{title}", method = RequestMethod.GET)
    public String save(@PathVariable("title") String title) {

        Movie movie = new Movie();
        movie.setReleased(2017);
        movie.setTagline("Taggish");
        movie.setTitle(title);

        service.save(movie);

        return "saved";
    }

}
