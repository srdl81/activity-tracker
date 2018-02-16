package ams.labs.service;

import ams.labs.dto.FavoriteDTO;
import ams.labs.entity.Favorit;
import ams.labs.entity.Annons;
import ams.labs.entity.Anvandare;
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
    private AnnonsService annonsService;

    @Autowired
    private FavoriteRepository repository;

    public void save(Favorit favorit) {
        repository.save(favorit);
    }

    public List<Favorit> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void saveFavoriteRelation(Long userId, FavoriteDTO dto) {
        Favorit favoritRelation = repository.findFavoriteFor(userId, dto.getId());
        if (favoritRelation == null && !dto.isFavoriteToggled()) {
            throw new ModelNotFoundException(String.format("Relation or Node does not exist, nothing to delete. id=%s, toggled=%s", dto.getId(), dto.isFavoriteToggled()));
        }

        if (dto.isFavoriteToggled()) {
            repository.save(getOrCreateFavoriteRelation(userId, dto, favoritRelation));
        } else {
            repository.delete(favoritRelation);
        }
    }

    /**
     * If Relation does not exist, create it.
     * @param userId
     * @param dto
     * @param favoritRelation
     * @return
     */
    private Favorit getOrCreateFavoriteRelation(Long userId, FavoriteDTO dto, Favorit favoritRelation) {
        return favoritRelation == null ? createNewFavorite(userId, dto) : favoritRelation;

    }

    private Favorit createNewFavorite(Long userId, FavoriteDTO dto) {
        Anvandare anvandare = userService.fetchUser(userId);
        Annons job = annonsService.fetchJobAdvertisement(dto.getId());
        return new Favorit(anvandare, job);
    }


}
