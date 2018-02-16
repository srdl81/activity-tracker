package ams.labs.repository;


import ams.labs.entity.Yrke;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface YrkeRepository extends GraphRepository<Yrke> {
    Yrke findByYrkeId(String id);
}
