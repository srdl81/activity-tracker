package ams.labs.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Collection;


@RelationshipEntity(type = "ACTED_IN")
public class Role {

    @GraphId Long id;
    private Collection<String> roles;

    @StartNode
    private Person person;

    @EndNode
    private Movie movie;

    public Role() {
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public Person getPerson() {
        return person;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
