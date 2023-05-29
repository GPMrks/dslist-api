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
    @JoinColumn(name = "gameList_id")
    private GameList gameList;

    public BelongingId() {
    }

    public BelongingId(Game game, GameList gameList) {
        this.game = game;
        this.gameList = gameList;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameList getGameList() {
        return gameList;
    }

    public void setGameList(GameList gameList) {
        this.gameList = gameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BelongingId that = (BelongingId) o;
        return game.equals(that.game) && gameList.equals(that.gameList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, gameList);
    }
}
