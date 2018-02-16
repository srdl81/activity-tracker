package ams.labs.service;


import ams.labs.dto.AnnonsDTO;
import ams.labs.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ams.labs.util.GeneralUtil.getCurrentDate;

@Service
@Transactional
public class ActivityService {
    private final static Logger log = LoggerFactory.getLogger(ActivityService.class);

    @Autowired
    private PlatsService locationService;

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnnonsService annonsService;

    @Autowired
    private ArbetsgivarService arbetsgivarService;

    @Autowired
    private WatchedService watchedService;

    public Annons logActivity(AnnonsDTO annonsDTO, Long userId) {

        Yrke yrke =  professionService.fetchProfession(annonsDTO.getYrkesroll());
        Plats plats = locationService.fetchPlats(annonsDTO.getErbjudenArbetsplats());

        Annons job = annonsService.fetchOrSaveJobAdvertisement(annonsDTO, yrke, plats);

        Anvandare anvandare = userService.fetchUser(userId);
        if (anvandare.getTittat() == null || anvandare.getTittat().isEmpty() || hasNotWatched(anvandare, job)) {
            watchedService.save(new Tittat(anvandare, job, getCurrentDate()));
        } else {
            log.info(String.format("Will not log this activity ('%s', '%s') because it has already been booked.", anvandare.getUserId(), job.getAnnonsId()));
            return null;
        }

        Arbetsgivare arbetsgivare = arbetsgivarService.fetchEmployer(annonsDTO);
        arbetsgivare.advertise(job);
        arbetsgivarService.save(arbetsgivare);

        return job;
    }

    private boolean hasNotWatched(Anvandare anvandare, Annons job) {
        return anvandare.getTittat().stream()
                .anyMatch(watched -> !watched.getAnnons().getAnnonsId()
                        .equalsIgnoreCase(job.getAnnonsId()));
    }

}
