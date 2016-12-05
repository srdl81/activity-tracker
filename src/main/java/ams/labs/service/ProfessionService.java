package ams.labs.service;


import ams.labs.model.Profession;
import ams.labs.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
