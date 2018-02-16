package ams.labs.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;


@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Yrke {

    @GraphId Long id;
    private String yrkeId;
    private String name;

    public String getYrkeId() {
        return yrkeId;
    }

    public void setYrkeId(String yrkeId) {
        this.yrkeId = yrkeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
