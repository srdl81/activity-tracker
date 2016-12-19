package ams.labs.entity;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class JobAdsResult {

    private String jobAdvertisementId;
    private Integer views;

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getJobAdvertisementId() {
        return jobAdvertisementId;
    }

    public void setJobAdvertisementId(String jobAdvertisementId) {
        this.jobAdvertisementId = jobAdvertisementId;
    }
}
