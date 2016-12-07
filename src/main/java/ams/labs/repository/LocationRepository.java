package ams.labs.repository;

import ams.labs.entity.Location;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends GraphRepository<Location> {
    Location findByLocationId(Long locationId);
}
