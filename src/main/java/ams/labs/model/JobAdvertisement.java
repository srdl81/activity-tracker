package ams.labs.model;

import org.neo4j.ogm.annotation.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class JobAdvertisement {

    @GraphId Long id;
    private Long jobAdvertisementId;

    @Relationship(type = "LOCATED_IN")
    private Location location;

    @Relationship(type = "HAS_A")
    private Profession profession;

    private Employer employer;

    public JobAdvertisement() { }

    public Long getJobAdvertisementId() {
        return jobAdvertisementId;
    }

    public void setJobAdvertisementId(Long jobAdvertisementId) {
        this.jobAdvertisementId = jobAdvertisementId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
