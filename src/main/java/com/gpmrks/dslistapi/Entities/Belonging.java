package com.gpmrks.dslistapi.Entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_belonging")
public class Belonging {

    @EmbeddedId
    private BelongingId belongingId = new BelongingId();
    private Integer position;

    public Belonging() {
    }

    public Belonging(Game game, GameList gameList, Integer position) {
        belongingId.setGame(game);
        belongingId.setGameList(gameList);
        this.position = position;
    }

    public BelongingId getBelongingId() {
        return belongingId;
    }

    public void setBelongingId(BelongingId belongingId) {
        this.belongingId = belongingId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Belonging belonging = (Belonging) o;
        return belongingId.equals(belonging.belongingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(belongingId);
    }
}
