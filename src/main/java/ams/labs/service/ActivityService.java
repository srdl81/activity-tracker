package ams.labs.service;


import ams.labs.dto.MatchResultDTO;
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
    private LocationService locationService;

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private UserService userService;

    @Autowired
    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private WatchedService watchedService;

    public JobAdvertisement logActivity(MatchResultDTO matchResultDTO, Long userId) {

        JobAdvertisement job = jobAdvertisementService.
                fetchJobAdvertisement(matchResultDTO, getProfession(matchResultDTO), getLocation(matchResultDTO));

        User user = userService.fetchUser(userId);
        if (user.getWatched() == null || user.getWatched().isEmpty() || hasNotWatched(user, job)) {
            watchedService.save(new Watched(user, job, getCurrentDate()));
        } else {
            log.info(String.format("Will not log this activity ('%s', '%s') because it has already been booked.", user.getUserId(), job.getJobAdvertisementId()));
            return null;
        }

        Employer employer = employerService.fetchEmployer(matchResultDTO);
        employer.advertise(job);
        employerService.save(employer);

        return job;
    }

    private boolean hasNotWatched(User user, JobAdvertisement job) {
        return user.getWatched().stream()
                .anyMatch(watched -> !watched.getJobAdvertisement().getJobAdvertisementId()
                        .equalsIgnoreCase(job.getJobAdvertisementId()));
    }

    private Location getLocation(MatchResultDTO matchResultDTO) {
        return locationService.fetchLocation(matchResultDTO.getErbjudenArbetsplats());
    }

    private Profession getProfession(MatchResultDTO matchResultDTO) {
        return professionService.fetchProfession(matchResultDTO.getYrkesroll());
    }

}
