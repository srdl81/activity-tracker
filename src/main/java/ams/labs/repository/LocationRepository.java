package ams.labs.repository;

import ams.labs.model.Location;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends GraphRepository<Location> {
    Location findByName(String name);
}
