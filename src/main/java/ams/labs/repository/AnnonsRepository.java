package ams.labs.repository;

import ams.labs.entity.AnnonsResult;
import ams.labs.entity.Annons;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "jobAdvertisements", path = "jobAdvertisements")
public interface AnnonsRepository extends GraphRepository<Annons> {
    Annons findByAnnonsId(@Param("annonsId") String annonsId);

    @Query("MATCH (job:Annons)<-[:WATCHED_AT]-(u:Anvandare) " +
           "RETURN job.annonsId AS annonsId, count(*) AS views " +
           "ORDER BY views DESC LIMIT 10")
    List<AnnonsResult> fetchMostWatchedAnnonser();

    @Query("MATCH (u:Anvandare)-[WATCHED_AT]->(job:Annons)-[LOCATED_IN]->(Plats {platsId: {platsId} }) " +
           "RETURN job.annonsId AS annonsId, count(*) AS views " +
           "ORDER BY views DESC LIMIT 10")
    List<AnnonsResult> fetchMostWatchedJobAdsByPlats(@Param("platsId") String platsId);

    @Query("MATCH (u:Anvandare)-[WATCHED_AT]->(job:Annons)-[HAS_A]->(yrke:Yrke {yrkeId:{yrkeId}}) " +
           "RETURN job.annonsId AS annonsId, count(*) AS views " +
           "ORDER BY views DESC LIMIT 10")
    List<AnnonsResult> fetchMostWatchedJobAdsByYrke(@Param("yrkeId") String yrkeId);

    @Query("MATCH (employer:Arbetsgivare {arbetsgivarId:{arbetsgivarId}})-[ADVERTISE]->(job:Annons)<-[WATCHED_AT]-(u:Anvandare) " +
           "RETURN job.annonsId AS annonsId, count(*) AS views " +
           "ORDER BY views DESC LIMIT 10")
    List<AnnonsResult> fetchMostWatchedJobAdsByArbetsgivare(@Param("arbetsgivarId") Long arbetsgivarId);

}


