package ams.labs.service;

import ams.labs.model.Employer;
import ams.labs.repository.EmployerRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
