package ams.labs.service;

import ams.labs.dto.FavoriteDTO;
import ams.labs.entity.Favorite;
import ams.labs.entity.JobAdvertisement;
import ams.labs.entity.User;
import ams.labs.exception.ModelNotFoundException;
import ams.labs.repository.FavoriteRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FavoriteService {

    @Autowired
    private UserService userService;

    @Autowired
    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    private FavoriteRepository repository;

    public void save(Favorite favorite) {
        repository.save(favorite);
    }

    public List<Favorite> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void saveFavoriteRelation(Long userId, FavoriteDTO dto) {
        Favorite favoriteRelation = repository.findFavoriteFor(userId, dto.getId());
        if (favoriteRelation == null && !dto.isFavoriteToggled()) {
            throw new ModelNotFoundException(String.format("Relation or Node does not exist, nothing to delete. id=%s, toggled=%s", dto.getId(), dto.isFavoriteToggled()));
        }

        if (dto.isFavoriteToggled()) {
            repository.save(getOrCreateFavoriteRelation(userId, dto, favoriteRelation));
        } else {
            repository.delete(favoriteRelation);
        }
    }

    /**
     * If Relation does not exist, create it.
     * @param userId
     * @param dto
     * @param favoriteRelation
     * @return
     */
    private Favorite getOrCreateFavoriteRelation(Long userId, FavoriteDTO dto, Favorite favoriteRelation) {
        return favoriteRelation == null ? createNewFavorite(userId, dto) : favoriteRelation;

    }

    private Favorite createNewFavorite(Long userId, FavoriteDTO dto) {
        User user = userService.fetchUser(userId);
        JobAdvertisement job = jobAdvertisementService.fetchJobAdvertisement(dto.getId());
        return new Favorite(user, job);
    }


}
