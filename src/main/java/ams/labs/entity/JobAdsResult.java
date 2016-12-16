package ams.labs.entity;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class JobAdsResult {

    Long jobAdvertisementId;
    Integer views;

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Long getJobAdvertisementId() {
        return jobAdvertisementId;
    }

    public void setJobAdvertisementId(Long jobAdvertisementId) {
        this.jobAdvertisementId = jobAdvertisementId;
    }
}
