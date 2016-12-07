package ams.labs.service;

import ams.labs.model.ErbjudenArbetsplats;
import ams.labs.model.Location;
import ams.labs.repository.LocationRepository;
import ams.labs.util.Converter;
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

    public Location findByLocationId(Long locationId) {
        return repository.findByLocationId(locationId);
    }

    public List<Location> findAll() {
        Iterable<Location> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }


    public Location fetchLocation(ErbjudenArbetsplats erbjudenArbetsplats) {

        Long locationId = Converter.convertToLong(erbjudenArbetsplats.getKommun());

        Location location = repository.findByLocationId(locationId);
        if (location == null) {
            location = new Location();
            location.setLocationId(locationId);
            location.setName(erbjudenArbetsplats.getKommun().getNamn());
            repository.save(location);
        }

        return location;
    }
}
