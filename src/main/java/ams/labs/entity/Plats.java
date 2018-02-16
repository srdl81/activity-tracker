package ams.labs.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Plats {

    @GraphId Long id;
    private String platsId;
    private String namn;

    public Plats(String namn) {
        this.namn = namn;
    }

    public Plats() {}

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getPlatsId() {
        return platsId;
    }

    public void setPlatsId(String platsId) {
        this.platsId = platsId;
    }
}
