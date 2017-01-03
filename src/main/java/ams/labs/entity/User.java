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

    @Relationship(type = "WATCHED_AT")
    private List<Watched> watched = new ArrayList<>();

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

}
