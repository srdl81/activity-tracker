package ams.labs.service;


import ams.labs.entity.JobAdsResult;
import ams.labs.entity.JobAdvertisement;
import ams.labs.entity.Location;
import ams.labs.dto.MatchResultDTO;
import ams.labs.entity.Profession;
import ams.labs.repository.JobAdvertisementRepository;
import ams.labs.util.Converter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ams.labs.util.Converter.*;

@Service
@Transactional
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

    public  List<JobAdsResult> fetchMostWatchedJobAdvertisements() {
        return repository.fetchMostWatchedJobAdvertisements();
    }

    public List<JobAdsResult> fetchMostWatchedJobAdsByForProfession(String professionId) {
        return repository.fetchMostWatchedJobAdsForProfession(professionId);
    }

    public List<JobAdsResult> fetchMostWatchedJobAdsForLocation(String locationId) {
        return repository.fetchMostWatchedJobAdsForLocation(locationId);
    }

    public List<JobAdsResult> fetchMostWatchedJobAdsByEmployer(Long employerId) {
        return repository.fetchMostWatchedJobAdsByEmployer(employerId);
    }

    public JobAdvertisement fetchJobAdvertisement(MatchResultDTO matchResultDTO, Profession profession, Location location) {

        Long id = convertToLong(matchResultDTO.getId());

        JobAdvertisement job = repository.findByJobAdvertisementId(id);
        if (job == null) {
            job = new JobAdvertisement();
            job.setJobAdvertisementId(id);
            job.setLocation(location);
            job.setProfession(profession);
            repository.save(job);
        }

        return job;
    }
}
