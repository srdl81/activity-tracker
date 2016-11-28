package ams.labs.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Person {

    @GraphId private Long id;

    private String name;
    private int born;

    private Person() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Person(String name) {
        this.name = name;
    }

    @Relationship(type = "ACTED_IN", direction = Relationship.UNDIRECTED)
    public Set<Movie> movies;

    public void actedIn(Movie movie) {
        if (movies == null) {
            movies = new HashSet<>();
        }

        movies.add(movie);
    }

    public String toString() {

        return this.name + "'s movies => "
                + Optional.ofNullable(this.movies).orElse(
                Collections.emptySet()).stream().map(
                movie -> movie.getTitle()).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public int getBorn() {
        return born;
    }
}
