package ams.labs.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "FAVORITE_MARKED")
public class Favorit {

    @GraphId
    private Long id;

    @StartNode
    private Anvandare anvandare;

    @EndNode
    private Annons annons;

    public Favorit() {
    }

    public Favorit(Anvandare anvandare, Annons annons) {
        this.anvandare = anvandare;
        this.annons = annons;
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
}
