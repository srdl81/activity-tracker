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
public class Arbetsgivare {

    @GraphId Long id;

    private Long arbetsgivarId;
    private Long organisationsnummer;
    private String namn;

    @Relationship(type = "ADVERTISE", direction = Relationship.OUTGOING)
    List<Annons> annonser;

    public void advertise(Annons annons) {
        if (this.annonser == null) {
            this.annonser = new ArrayList<>();
        }

        this.annonser.add(annons);
    }

    public Long getArbetsgivarId() {
        return arbetsgivarId;
    }

    public void setArbetsgivarId(Long arbetsgivarId) {
        this.arbetsgivarId = arbetsgivarId;
    }

    public Long getOrganisationsnummer() {
        return organisationsnummer;
    }

    public void setOrganisationsnummer(Long organisationsnummer) {
        this.organisationsnummer = organisationsnummer;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }
}
