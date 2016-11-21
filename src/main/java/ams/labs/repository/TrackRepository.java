package ams.labs.repository;


import ams.labs.model.Person;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface TrackRepository extends GraphRepository<Person> {
    Person findByName(String name);
}
