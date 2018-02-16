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
public class UserService {

    final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AnvardarRepository repository;

    public void save(Anvandare anvandare) {
        repository.save(anvandare);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Anvandare findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    public List<Anvandare> findAll() {
        Iterable<Anvandare> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }

    public Anvandare fetchUser(Long userId) {
        Anvandare anvandare = repository.findByUserId(userId);
        if (anvandare == null) {
            anvandare = new Anvandare();
            anvandare.setUserId(userId);
            repository.save(anvandare);
        }

        return anvandare;
    }
}
