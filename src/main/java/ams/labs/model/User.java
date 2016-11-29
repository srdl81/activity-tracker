package ams.labs.model;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class User {

    @GraphId Long id;
    private String userId;

    @Relationship(type = "LOOKED_AT")
    private List<JobAdvertisement> jobAdvertisements;

    public void lookedAt(JobAdvertisement jobAdvertisement) {
        if (jobAdvertisements == null) {
            jobAdvertisements = new ArrayList<>();
        }

        jobAdvertisements.add(jobAdvertisement);
    }

    public User() { }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<JobAdvertisement> getJobAdvertisements() {
        return jobAdvertisements;
    }

    public void setJobAdvertisements(List<JobAdvertisement> jobAdvertisements) {
        this.jobAdvertisements = jobAdvertisements;
    }

}
