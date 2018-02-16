package ams.labs.service;


import ams.labs.dto.PropertyDTO;
import ams.labs.entity.Yrke;
import ams.labs.repository.YrkeRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class YrkeService {

    private final static Logger log = LoggerFactory.getLogger(YrkeService.class);

    @Autowired
    private YrkeRepository repository;

    public Yrke findByYrkeId(String yrkeId) {
        return repository.findByYrkeId(yrkeId);
    }

    public void save(Yrke yrke) {
        repository.save(yrke);
    }

    public List<Yrke> findAll() {
        Iterable<Yrke> userIterable = repository.findAll();

        return Lists.newArrayList(userIterable);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Yrke fetchYrke(PropertyDTO propertyDTO) {
        String yrkeId = propertyDTO.getId();
        Yrke yrke = repository.findByYrkeId(yrkeId);
        if (yrke == null) {
            yrke = new Yrke();
            yrke.setYrkeId(yrkeId);
            yrke.setName(propertyDTO.getNamn());
            repository.save(yrke);
        }

        return yrke;
    }

}
