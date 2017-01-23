package ams.labs.entity;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class User {

    @GraphId Long id;
    private Long userId;

    @Relationship(type = "WATCHED_AT", direction = Relationship.OUTGOING)
    private List<Watched> watched = new ArrayList<>();

    @Relationship(type = "FAVORITE_MARKED", direction = Relationship.OUTGOING)
    private List<Favorite> favorites = new ArrayList<>();

    public User() { }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Watched> getWatched() {
        return watched;
    }

    public void setWatched(List<Watched> watched) {
        this.watched = watched;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
}
