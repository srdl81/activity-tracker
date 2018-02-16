package ams.labs.repository;


import ams.labs.entity.Anvandare;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AnvardarRepository extends GraphRepository<Anvandare> {
    Anvandare findByUserId(Long id);
}
