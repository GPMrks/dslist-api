package com.gpmrks.dslistapi.Dto;

public class BelongingForm {

    private Long gameId;
    private Long listId;

    public BelongingForm() {
    }

    public BelongingForm(Long gameId, Long listId) {
        this.gameId = gameId;
        this.listId = listId;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getListId() {
        return listId;
    }

}
