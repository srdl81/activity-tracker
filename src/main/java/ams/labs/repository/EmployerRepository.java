package ams.labs.repository;


import ams.labs.entity.Employer;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface EmployerRepository extends GraphRepository<Employer> {
    Employer findByEmployerId(Long locationId);
}
