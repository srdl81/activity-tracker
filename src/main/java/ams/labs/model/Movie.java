package ams.labs.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.Set;

@NodeEntity
public class Movie {

    @GraphId
    private Long id;

    private String title;

    private int released;
    private String tagline;

//    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
//    public Set<Role> roles;

    public Movie() { }

//    public Collection<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

    public String getTitle() {
        return title;
    }

    public int getReleased() {
        return released;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

}
