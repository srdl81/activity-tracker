package ams.labs.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FavoriteDTO {

    @NotNull
    @Size(min=6, max=15)
    private String id;

    @NotNull
    private boolean favoriteToggled;

    public FavoriteDTO(String id, boolean favoriteToggled) {
        this.id = id;
        this.favoriteToggled = favoriteToggled;
    }

    public FavoriteDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFavoriteToggled() {
        return favoriteToggled;
    }

    public void setFavoriteToggled(boolean favoriteToggled) {
        this.favoriteToggled = favoriteToggled;
    }

}
