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
public class ProfessionService {

    private final static Logger log = LoggerFactory.getLogger(ProfessionService.class);

    @Autowired
    private YrkeRepository repository;

    public Yrke findByProfessionId(String professionId) {
        return repository.findByYrkeId(professionId);
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

    public Yrke fetchProfession(PropertyDTO params) {
        String professionId = params.getId();
        Yrke yrke = repository.findByYrkeId(professionId);
        if (yrke == null) {
            yrke = new Yrke();
            yrke.setYrkeId(professionId);
            yrke.setName(params.getNamn());
            repository.save(yrke);
        }

        return yrke;
    }

}
