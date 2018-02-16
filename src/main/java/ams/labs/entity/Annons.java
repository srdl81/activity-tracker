package ams.labs.entity;

import org.neo4j.ogm.annotation.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Annons {

    @GraphId Long id;
    private String annonsId;

    @Relationship(type = "LOCATED_IN", direction = Relationship.OUTGOING)
    private Plats plats;

    @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
    private Yrke yrke;

    public Annons() { }

    public String getAnnonsId() {
        return annonsId;
    }

    public void setAnnonsId(String annonsId) {
        this.annonsId = annonsId;
    }

    public Plats getPlats() {
        return plats;
    }

    public void setPlats(Plats plats) {
        this.plats = plats;
    }

    public Yrke getYrke() {
        return yrke;
    }

    public void setYrke(Yrke yrke) {
        this.yrke = yrke;
    }
}
