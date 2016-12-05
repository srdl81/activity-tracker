package ams.labs.repository;


import ams.labs.model.Employer;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface EmployerRepository extends GraphRepository<Employer> {
    Employer findByEmployerId(Long locationId);
}
