package ams.labs.service;

import ams.labs.entity.Watched;
import ams.labs.repository.WatchedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WatchedService {

    @Autowired
    private WatchedRepository repository;

    public void save(Watched watched) {
        repository.save(watched);
    }

}
