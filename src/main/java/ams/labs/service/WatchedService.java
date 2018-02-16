package ams.labs.service;

import ams.labs.entity.Tittat;
import ams.labs.repository.TittatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WatchedService {

    @Autowired
    private TittatRepository repository;

    public void save(Tittat tittat) {
        repository.save(tittat);
    }

}
