package ams.labs.service;


import ams.labs.model.Movie;
import ams.labs.model.Person;
import ams.labs.repository.MovieRepository;
import ams.labs.repository.PersonRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository repository;

    public void save(Movie movie) {
        repository.save(movie);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Movie findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Movie> findAll() {
        Iterable<Movie> personIterable = repository.findAll();

        return Lists.newArrayList(personIterable);
    }
}
