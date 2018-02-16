package ams.labs.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

@RelationshipEntity(type = "WATCHED_AT")
public class Tittat {

    @GraphId
    private Long id;

    @StartNode
    private Anvandare anvandare;

    @EndNode
    private Annons annons;

    @DateString
    private Date date;

    public Tittat() {}

    public Tittat(Anvandare anvandare, Annons annons, Date currentDate) {
        this.anvandare = anvandare;
        this.annons = annons;
        this.date = currentDate;

        this.anvandare.getTittat().add(this);
    }

    public Anvandare getAnvandare() {
        return anvandare;
    }

    public void setAnvandare(Anvandare anvandare) {
        this.anvandare = anvandare;
    }

    public Annons getAnnons() {
        return annons;
    }

    public void setAnnons(Annons annons) {
        this.annons = annons;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
