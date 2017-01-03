package ams.labs.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

@RelationshipEntity(type = "WATCHED_AT")
public class Watched {

    @GraphId
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private JobAdvertisement jobAdvertisement;

    @DateString
    private Date date;

    public Watched() {}

    public Watched(User user, JobAdvertisement jobAdvertisement, Date currentDate) {
        this.user = user;
        this.jobAdvertisement = jobAdvertisement;
        this.date = currentDate;

        this.user.getWatched().add(this);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
