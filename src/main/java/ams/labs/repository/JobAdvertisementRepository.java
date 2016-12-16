package ams.labs.repository;

import ams.labs.entity.JobAdsResult;
import ams.labs.entity.JobAdvertisement;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "jobAdvertisements", path = "jobAdvertisements")
public interface JobAdvertisementRepository extends GraphRepository<JobAdvertisement> {
    JobAdvertisement findByJobAdvertisementId(@Param("jobAdvertisementId") Long jobAdvertisementId);

    @Query("MATCH (job:JobAdvertisement)<-[:LOOKED_AT]-(u:User) " +
           "RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS views " +
           "ORDER BY views DESC LIMIT 10")
    List<JobAdsResult> fetchMostWatchedJobAdvertisements();

    @Query("MATCH (u:User)-[LOOKED_AT]->(job:JobAdvertisement)-[LOCATED_IN]->(Location {locationId: {locationId} }) " +
           "RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS views " +
           "ORDER BY views DESC LIMIT 10")
    List<JobAdsResult> fetchMostWatchedJobAdsForLocation(@Param("locationId") Long locationId);

    @Query("MATCH (u:User)-[LOOKED_AT]->(job:JobAdvertisement)-[HAS_A]->(profession:Profession {professionId:{professionId}}) " +
           "RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS views " +
           "ORDER BY views DESC LIMIT 10")
    List<JobAdsResult> fetchMostWatchedJobAdsForProfession(@Param("professionId") Long professionId);

    @Query("MATCH (employer:Employer {employerId:{employerId}})-[ADVERTISE]->(job:JobAdvertisement)<-[LOOKED_AT]-(u:User) " +
           "RETURN job.jobAdvertisementId AS jobAdvertisementId, count(*) AS views " +
           "ORDER BY views DESC LIMIT 10")
    List<JobAdsResult> fetchMostWatchedJobAdsByEmployer(@Param("employerId") Long employerId);

}


