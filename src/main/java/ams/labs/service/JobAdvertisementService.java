package ams.labs.service;


import ams.labs.model.JobAdvertisement;
import ams.labs.repository.JobAdvertisementRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobAdvertisementService {

    final Logger logger = LoggerFactory.getLogger(JobAdvertisementService.class);

    @Autowired
    private JobAdvertisementRepository repository;

    public void save(JobAdvertisement jobAdvertisement) {
        repository.save(jobAdvertisement);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public JobAdvertisement findByJobAdvertisementId(Long jobAdvertisementId) {
        return repository.findByJobAdvertisementId(jobAdvertisementId);
    }

    public List<JobAdvertisement> findAll() {
        Iterable<JobAdvertisement> jobsIterable = repository.findAll();

        return Lists.newArrayList(jobsIterable);
    }

    public List<Map<String, Object>> fetchMostWatchedJobAdvertisements() {
        return repository.fetchMostWatchedJobAdvertisements();
    }

    public List<Map<String, Object>> fetchMostWatchedJobAdsByForProfession(Long professionId) {
        return repository.fetchMostWatchedJobAdsForProfession(professionId);
    }

    public List<Map<String, Object>> fetchMostWatchedJobAdsForLocation(Long locationId) {
        return repository.fetchMostWatchedJobAdsForLocation(locationId);
    }
}
