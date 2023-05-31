package com.gpmrks.dslistapi.Projections;

public interface MinimalGameInfoProjection {

    Long getId();
    String getTitle();
    Integer getGameYear();
    String getImageUrl();
    String getShortDescription();
    Integer getPosition();

}
