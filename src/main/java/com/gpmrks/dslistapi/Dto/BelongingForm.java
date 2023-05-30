package com.gpmrks.dslistapi.Dto;

public class BelongingForm {

    private Long gameId;

    private Long listId;
    private Integer position;

    public BelongingForm() {
    }

    public BelongingForm(Long gameId, Long listId, Integer position) {
        this.gameId = gameId;
        this.listId = listId;
        this.position = position;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getListId() {
        return listId;
    }

    public Integer getPosition() {
        return position;
    }
}
