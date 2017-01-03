package ams.labs.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;


@NodeEntity
@JsonIdentityInfo(generator=JSOGGenerator.class)
public class Employer {

    @GraphId Long id;

    private Long employerId;
    private Long registrationNumber;
    private String name;

    @Relationship(type = "ADVERTISE", direction = Relationship.OUTGOING)
    List<JobAdvertisement> jobAdvertisements;

    public void advertise(JobAdvertisement jobAdvertisement) {
        if (jobAdvertisements == null) {
            jobAdvertisements = new ArrayList<>();
        }

        jobAdvertisements.add(jobAdvertisement);
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
