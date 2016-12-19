package ams.labs.service;

import ams.labs.entity.Employer;
import ams.labs.dto.MatchResultDTO;
import ams.labs.repository.EmployerRepository;
import ams.labs.util.Converter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ams.labs.util.Converter.*;

@Service
@Transactional
public class EmployerService {

    @Autowired
    private EmployerRepository repository;

    public Employer findByEmployerId(Long employerId) {
        return repository.findByEmployerId(employerId);
    }

    public void save(Employer employer) {
        repository.save(employer);
    }

    public List<Employer> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Employer fetchEmployer(MatchResultDTO matchResultDTO) {

        Long id = convertToLong(matchResultDTO.getArbetsgivareId());

        Employer employer = repository.findByEmployerId(id);
        if (employer == null) {
            employer = new Employer();
            employer.setEmployerId(id);
            employer.setName(matchResultDTO.getArbetsgivarenamn());
            employer.setRegistrationNumber(convertToLong(matchResultDTO.getOrganisationsnummer()));

            repository.save(employer);
        }

        return employer;
    }
}
