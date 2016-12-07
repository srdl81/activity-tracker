package ams.labs.service;

import ams.labs.dto.MatchResultDTO;
import ams.labs.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityService {

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

    public JobAdvertisement logActivity(MatchResultDTO matchResultDTO, Long userId) {

        JobAdvertisement job = jobAdvertisementService.
                fetchJobAdvertisement(matchResultDTO, getProfession(matchResultDTO), getLocation(matchResultDTO));

        User user = userService.fetchUser(userId);
        user.lookedAt(job);
        userService.save(user);

        Employer employer = employerService.fetchEmployer(matchResultDTO);
        employer.advertise(job);
        employerService.save(employer);

        return job;
    }

    private Location getLocation(MatchResultDTO matchResultDTO) {
        return locationService.fetchLocation(matchResultDTO.getErbjudenArbetsplats());
    }

    private Profession getProfession(MatchResultDTO matchResultDTO) {
        return professionService.fetchProfession(matchResultDTO.getYrkesroll());
    }

}
