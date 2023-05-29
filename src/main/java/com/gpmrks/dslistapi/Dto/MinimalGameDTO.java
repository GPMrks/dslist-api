package com.gpmrks.dslistapi.Dto;

import com.gpmrks.dslistapi.Entities.Game;

public class MinimalGameDTO {

    private Long id;
    private String title;
    private Integer year;
    private String imageUrl;
    private String shortDescription;

    public MinimalGameDTO() {
    }

    public MinimalGameDTO(Game game) {
        id = game.getId();
        title = game.getTitle();
        year = game.getYear();
        imageUrl = game.getImageUrl();
        shortDescription = game.getShortDescription();
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
