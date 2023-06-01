package com.gpmrks.dslistapi.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BelongingId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListOfGames listOfGames;

    public BelongingId() {
    }

    public BelongingId(Game game, ListOfGames listOfGames) {
        this.game = game;
        this.listOfGames = listOfGames;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ListOfGames getGameList() {
        return listOfGames;
    }

    public void setGameList(ListOfGames listOfGames) {
        this.listOfGames = listOfGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelongingId that = (BelongingId) o;
        return game.equals(that.game) && listOfGames.equals(that.listOfGames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, listOfGames);
    }
}
