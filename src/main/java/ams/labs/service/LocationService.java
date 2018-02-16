package ams.labs.service;

import ams.labs.dto.ErbjudenArbetsplatsDTO;
import ams.labs.entity.Plats;
import ams.labs.repository.PlatsRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationService {

    @Autowired
    private PlatsRepository repository;

    public void save(Plats plats) {
        repository.save(plats);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Plats findByLocationId(String locationId) {
        return repository.findByPlatsId(locationId);
    }

    public List<Plats> findAll() {
        Iterable<Plats> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }


    public Plats fetchLocation(ErbjudenArbetsplatsDTO erbjudenArbetsplats) {

        String id = erbjudenArbetsplats.getKommun().getId();

        Plats plats = repository.findByPlatsId(id);
        if (plats == null) {
            plats = new Plats();
            plats.setPlatsId(id);
            plats.setNamn(erbjudenArbetsplats.getKommun().getNamn());
            repository.save(plats);
        }

        return plats;
    }
}
