package com.gpmrks.dslistapi.Dto;

import com.gpmrks.dslistapi.Entities.GameList;

import java.util.Objects;

public class GameListDTO {

    private Long id;
    private String name;

    public GameListDTO() {
    }

    public GameListDTO(GameList gameList) {
        id = gameList.getId();
        name = gameList.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
