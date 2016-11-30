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
    JobAdvertisement findByJobAdvertisementId(@Param("jobAdvertisementId") String jobAdvertisementId);

    @Query("MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+{title}+'.*') RETURN m")
    Collection<JobAdvertisement> findByTitleContaining(@Param("title") String title);

    @Query("MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) RETURN m.title as movie, collect(a.name) as cast LIMIT {limit}")
    List<Map<String,Object>> graph(@Param("limit") int limit);

    @Query("MATCH (job:JobAdvertisement)<-[:LOOKED_AT]-(User) RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS viewed ORDER BY viewed DESC LIMIT 10")
    List<Map<String,Object>> fetchMostWatchedJobAdvertisements();

    @Query("MATCH (User)-[:LOOKED_AT]->(job:JobAdvertisement) \n" +
           "WHERE job.place = {place}\n" +
           "RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS viewed ORDER BY viewed DESC LIMIT 10")
    List<Map<String,Object>> fetchMostWatchedJobAdsForPlace(@Param("place") String place);
}


