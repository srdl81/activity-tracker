package ams.labs.repository;

import ams.labs.model.JobAdvertisement;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RepositoryRestResource(collectionResourceRel = "jobAdvertisements", path = "jobAdvertisements")
public interface JobAdvertisementRepository extends GraphRepository<JobAdvertisement> {
    JobAdvertisement findByJobAdvertisementId(@Param("jobAdvertisementId") Long jobAdvertisementId);

    @Query("MATCH (job:JobAdvertisement)<-[:LOOKED_AT]-(User) RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS viewed ORDER BY viewed DESC LIMIT 10")
    List<Map<String,Object>> fetchMostWatchedJobAdvertisements();

    @Query("MATCH (User)-[LOOKED_AT]->(job:JobAdvertisement)-[LOCATED_IN]->(Location {locationId: {locationId} }) " +
           "RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS viewed ORDER BY viewed DESC LIMIT 10")
    List<Map<String,Object>> fetchMostWatchedJobAdsForLocation(@Param("locationId") Long locationId);

    @Query("MATCH ()-[LOOKED_AT]->(job:JobAdvertisement)-[HAS_A]->(profession:Profession {professionId:{professionId}}) " +
           "RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS viewed " +
           "ORDER BY viewed DESC LIMIT 10")
    List<Map<String,Object>> fetchMostWatchedJobAdsForProfession(@Param("professionId") Long professionId);
}


