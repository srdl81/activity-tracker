package ams.labs.service;


import ams.labs.dto.IdNamn;
import ams.labs.entity.Profession;
import ams.labs.repository.ProfessionRepository;
import ams.labs.util.Converter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {

    private final static Logger log = LoggerFactory.getLogger(ProfessionService.class);

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

    public Profession fetchProfession(IdNamn params) {
        Long professionId = Converter.convertToLong(params);

        Profession profession = repository.findByProfessionId(professionId);
        if (profession == null) {
            profession = new Profession();
            profession.setProfessionId(professionId);
            profession.setName(params.getNamn());
            repository.save(profession);
        }

        return profession;
    }

}
