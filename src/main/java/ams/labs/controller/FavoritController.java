package ams.labs.controller;

import ams.labs.dto.FavoriteDTO;
import ams.labs.service.FavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value = "Favorite Entity", description = "Toggle favorite")
@RestController
public class FavoritController {

    private final static Logger log = LoggerFactory.getLogger(FavoritController.class);

    @Autowired
    private FavoriteService favoriteService;

    @ApiOperation(value = "Log User favorite", nickname = "Log Favorite", produces = "application/json")
    @RequestMapping(value = "/log/activity/favorite", method = RequestMethod.POST)
    public void logFavorite(@Valid @RequestBody FavoriteDTO dto,
                            @RequestParam(value = "anvandare", defaultValue = "1001") Long anvandare) {

        log.info(String.format("logs favorite for anvandare '%s' with DTO '%s'", anvandare, dto));
        favoriteService.saveFavoriteRelation(anvandare, dto);
    }

    @ApiOperation(value = "Log User favorite", nickname = "Log Favorite", produces = "application/json")
    @RequestMapping(value = "/log/activity/favorite/test", method = RequestMethod.POST)
    public void logUserFavorite(@RequestParam(value = "dto", defaultValue = "6968823") String id,
                                @RequestParam(value = "user", defaultValue = "1001") Long user,
                                @RequestParam(value = "favorite", defaultValue = "true") boolean favoriteToggled) {

        favoriteService.saveFavoriteRelation(user, new FavoriteDTO(id, favoriteToggled));
    }
}
