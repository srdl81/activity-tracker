package ams.labs.repository;


import ams.labs.entity.User;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GraphRepository<User> {
    User findByUserId(Long id);
}
