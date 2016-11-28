package ams.labs.service;


import ams.labs.model.Person;
import ams.labs.repository.PersonRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository repository;

    public void save(Person person) {
        repository.save(person);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Person findByName(String name) {
        return repository.findByName(name);
    }

    public List<Person> findAll() {
        Iterable<Person> personIterable = repository.findAll();

        return Lists.newArrayList(personIterable);
    }
}
