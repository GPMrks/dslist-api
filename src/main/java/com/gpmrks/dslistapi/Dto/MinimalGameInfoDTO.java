package com.gpmrks.dslistapi.Dto;

import com.gpmrks.dslistapi.Entities.Game;
import com.gpmrks.dslistapi.Projections.MinimalGameInfoProjection;

public class MinimalGameInfoDTO {

    private Long id;
    private String title;
    private Integer year;
    private String imageUrl;
    private String shortDescription;

    public MinimalGameInfoDTO() {
    }

    public MinimalGameInfoDTO(Game game) {
        id = game.getId();
        title = game.getTitle();
        year = game.getYear();
        imageUrl = game.getImageUrl();
        shortDescription = game.getShortDescription();
    }

    public MinimalGameInfoDTO(MinimalGameInfoProjection minimalGameInfoProjection) {
        id = minimalGameInfoProjection.getId();
        title = minimalGameInfoProjection.getTitle();
        year = minimalGameInfoProjection.getYear();
        imageUrl = minimalGameInfoProjection.getImageUrl();
        shortDescription = minimalGameInfoProjection.getShortDescription();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String toString() {
        return "MinimalGameDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", imageUrl='" + imageUrl + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                '}';
    }
}
