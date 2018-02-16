package ams.labs.repository;

import ams.labs.entity.Favorit;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends GraphRepository<Favorit> {

    @Query("MATCH (Anvandare {anvandarId: {anvandarId}})-[f:FAVORITE_MARKED]->(Annons {annonsId: {annonsId}}) RETURN f")
    Favorit findFavoriteFor(@Param("anvandarId") Long anvandarId, @Param("annonsId") String annonsId);
}
