package ams.labs.service;


import ams.labs.entity.AnnonsResult;
import ams.labs.entity.Annons;
import ams.labs.entity.Plats;
import ams.labs.dto.AnnonsDTO;
import ams.labs.entity.Yrke;
import ams.labs.exception.ModelNotFoundException;
import ams.labs.repository.AnnonsRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnnonsService {

    final Logger logger = LoggerFactory.getLogger(AnnonsService.class);

    @Autowired
    private AnnonsRepository repository;

    public void save(Annons annons) {
        repository.save(annons);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Annons findByAnnonsId(String annonsId) {
        return repository.findByAnnonsId(annonsId);
    }

    public List<Annons> findAll() {
        Iterable<Annons> jobsIterable = repository.findAll();

        return Lists.newArrayList(jobsIterable);
    }

    public  List<AnnonsResult> fetchMostWatchedAnnonser() {
        return repository.fetchMostWatchedAnnonser();
    }

    public List<AnnonsResult> fetchMostWatchedJobAdsByForYrke(String professionId) {
        return repository.fetchMostWatchedJobAdsByYrke(professionId);
    }

    public List<AnnonsResult> fetchMostWatchedJobAdsForLocation(String locationId) {
        return repository.fetchMostWatchedJobAdsByPlats(locationId);
    }

    public List<AnnonsResult> fetchMostWatchedJobAdsByEmployer(Long employerId) {
        return repository.fetchMostWatchedJobAdsByArbetsgivare(employerId);
    }

    public Annons fetchOrSaveJobAdvertisement(AnnonsDTO annonsDTO, Yrke yrke, Plats plats) {

        String id = annonsDTO.getId();
        Annons job = repository.findByAnnonsId(id);
        if (job == null) {
            job = new Annons();
            job.setAnnonsId(id);
            job.setPlats(plats);
            job.setYrke(yrke);
            repository.save(job);
        }

        return job;
    }

    public Annons fetchJobAdvertisement(String jobAdsId) {

        Annons annons = repository.findByAnnonsId(jobAdsId);
        if (annons == null) {
            throw new ModelNotFoundException("Could not find JobAdvertisement with id: '%s'");
        }

        return annons;
    }
}
