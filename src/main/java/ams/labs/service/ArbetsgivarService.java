package ams.labs.service;

import ams.labs.entity.Arbetsgivare;
import ams.labs.dto.AnnonsDTO;
import ams.labs.repository.ArbetsgivarRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ams.labs.util.Converter.*;

@Service
@Transactional
public class ArbetsgivarService {

    @Autowired
    private ArbetsgivarRepository repository;

    public Arbetsgivare findByArbetsgivarId(Long arbetsgivarId) {
        return repository.findByArbetsgivarId(arbetsgivarId);
    }

    public void save(Arbetsgivare arbetsgivare) {
        repository.save(arbetsgivare);
    }

    public List<Arbetsgivare> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Arbetsgivare fetchEmployer(AnnonsDTO annonsDTO) {

        /**
         * arbetsgivarId visas den alltid? eller ska man använda sig av organisations
         */

        Long id = convertToLong("12345678910");

        Arbetsgivare arbetsgivare = repository.findByArbetsgivarId(id);
        if (arbetsgivare == null) {
            arbetsgivare = new Arbetsgivare();
            arbetsgivare.setArbetsgivarId(id);
            arbetsgivare.setNamn(annonsDTO.getArbetsgivarenamn());
            arbetsgivare.setOrganisationsnummer(convertToLong(annonsDTO.getOrganisationsnummer()));

            repository.save(arbetsgivare);
        }

        return arbetsgivare;
    }
}
