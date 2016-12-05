package ams.labs.service;


import ams.labs.model.Profession;
import ams.labs.repository.ProfessionRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {

    @Autowired
    private ProfessionRepository repository;

    public Profession findByProfessionId(Long professionId) {
        return repository.findByProfessionId(professionId);
    }

    public void save(Profession profession) {
        repository.save(profession);
    }

    public List<Profession> findAll() {
        Iterable<Profession> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
