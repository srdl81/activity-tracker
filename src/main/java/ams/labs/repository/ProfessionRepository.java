package ams.labs.repository;


import ams.labs.model.Profession;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends GraphRepository<Profession> {
    Profession findByProfessionId(Long id);
}
