package ams.labs.repository;


import ams.labs.entity.Watched;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface WatchedRepository extends GraphRepository<Watched> {

}
