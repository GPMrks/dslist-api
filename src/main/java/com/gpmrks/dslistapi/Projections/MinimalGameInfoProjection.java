package com.gpmrks.dslistapi.Projections;

public interface MinimalGameInfoProjection {

    Long getId();
    String getTitle();
    Integer getYear();
    String getImageUrl();
    String getShortDescription();
    Integer getPosition();

}
