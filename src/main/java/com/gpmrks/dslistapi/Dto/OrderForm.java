package com.gpmrks.dslistapi.Dto;

public class OrderForm {

    private Long listId;
    private Long gameId;
    private int destinationIndex;

    public OrderForm() {
    }

    public OrderForm(Long listId, Long gameId, int destinationIndex) {
        this.listId = listId;
        this.gameId = gameId;
        this.destinationIndex = destinationIndex;
    }

    public Long getListId() {
        return listId;
    }

    public Long getGameId() {
        return gameId;
    }

    public int getDestinationIndex() {
        return destinationIndex;
    }
}
