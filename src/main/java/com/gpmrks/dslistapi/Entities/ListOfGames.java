package com.gpmrks.dslistapi.Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_list")
public class ListOfGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public ListOfGames() {
    }

    public ListOfGames(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOfGames listOfGames = (ListOfGames) o;
        return id.equals(listOfGames.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
