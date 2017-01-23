package ams.labs.repository;

import ams.labs.entity.Favorite;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends GraphRepository<Favorite> {

    @Query("MATCH (User {userId: {userId}})-[f:FAVORITE_MARKED]->(JobAdvertisement {jobAdvertisementId: {jobAdvertisementId}}) RETURN f")
    Favorite findFavoriteFor(@Param("userId") Long userId, @Param("jobAdvertisementId") String jobAdvertisementId);
}
