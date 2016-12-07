package ams.labs.repository;


import ams.labs.entity.Profession;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

public interface ProfessionRepository extends GraphRepository<Profession> {
    Profession findByProfessionId(Long id);
}
