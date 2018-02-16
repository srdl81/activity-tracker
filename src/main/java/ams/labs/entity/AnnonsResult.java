package ams.labs.entity;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class AnnonsResult {

    private String annonsId;
    private Integer views;

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getAnnonsId() {
        return annonsId;
    }

    public void setAnnonsId(String annonsId) {
        this.annonsId = annonsId;
    }
}
