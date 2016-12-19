package ams.labs.service;

import ams.labs.dto.ErbjudenArbetsplats;
import ams.labs.entity.Location;
import ams.labs.repository.LocationRepository;
import ams.labs.util.Converter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationRepository repository;

    public void save(Location location) {
        repository.save(location);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Location findByLocationId(String locationId) {
        return repository.findByLocationId(locationId);
    }

    public List<Location> findAll() {
        Iterable<Location> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }


    public Location fetchLocation(ErbjudenArbetsplats erbjudenArbetsplats) {

        String id = erbjudenArbetsplats.getKommun().getId();

        Location location = repository.findByLocationId(id);
        if (location == null) {
            location = new Location();
            location.setLocationId(id);
            location.setName(erbjudenArbetsplats.getKommun().getNamn());
            repository.save(location);
        }

        return location;
    }
}
