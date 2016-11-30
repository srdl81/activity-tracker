package ams.labs.service;

import ams.labs.model.Location;
import ams.labs.repository.LocationRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    public void save(Location location) {
        repository.save(location);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Location findByLocationName(String name) {
        return repository.findByName(name);
    }

    public List<Location> findAll() {
        Iterable<Location> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }



}
