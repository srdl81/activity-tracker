package ams.labs.service;


import ams.labs.model.Person;
import ams.labs.repository.TrackRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    final Logger logger = LoggerFactory.getLogger(TrackService.class);

    @Autowired
    private TrackRepository repository;

    public void save(Person person) {
        repository.save(person);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deletePerson(String name) {

        Person person = findByName(name);

        if(person == null) {
            throw new IllegalArgumentException(String.format("Person with name '%s' could not be found in database. " +
                    "Could not delete a person that does not exists in db.", name));
        }

        repository.delete(person);
    }

    public Person findByName(String name) {
        return repository.findByName(name);
    }

    public List<Person> findAll() {
        Iterable<Person> personIterable = repository.findAll();

        return Lists.newArrayList(personIterable);
    }
}
