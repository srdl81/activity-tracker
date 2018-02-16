package ams.labs.repository;


import ams.labs.entity.Arbetsgivare;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface ArbetsgivarRepository extends GraphRepository<Arbetsgivare> {
    Arbetsgivare findByArbetsgivarId(Long arbetsgivarId);
}
