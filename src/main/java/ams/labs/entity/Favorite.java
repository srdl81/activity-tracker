package ams.labs.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "FAVORITE_MARKED")
public class Favorite {

    @GraphId
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private JobAdvertisement jobAdvertisement;

    public Favorite() {
    }

    public Favorite(User user, JobAdvertisement jobAdvertisement) {
        this.user = user;
        this.jobAdvertisement = jobAdvertisement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobAdvertisement getJobAdvertisement() {
        return jobAdvertisement;
    }

    public void setJobAdvertisement(JobAdvertisement jobAdvertisement) {
        this.jobAdvertisement = jobAdvertisement;
    }
}
