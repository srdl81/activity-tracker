package ams.labs.service;


import ams.labs.entity.Anvandare;
import ams.labs.repository.AnvardarRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnvandarService {

    final Logger logger = LoggerFactory.getLogger(AnvandarService.class);

    @Autowired
    private AnvardarRepository repository;

    public void save(Anvandare anvandare) {
        repository.save(anvandare);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Anvandare findByUserId(Long id) {
        return repository.findByAnvandarId(id);
    }

    public List<Anvandare> findAll() {
        Iterable<Anvandare> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }

    public Anvandare fetchUser(Long anvandarId) {
        Anvandare anvandare = repository.findByAnvandarId(anvandarId);
        if (anvandare == null) {
            anvandare = new Anvandare();
            anvandare.setAnvandarId(anvandarId);
            repository.save(anvandare);
        }

        return anvandare;
    }
}
