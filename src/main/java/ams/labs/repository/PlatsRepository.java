package ams.labs.repository;

import ams.labs.entity.Plats;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PlatsRepository extends GraphRepository<Plats> {
    Plats findByPlatsId(String platsId);
}
