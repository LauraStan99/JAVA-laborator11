package com.example.lab11.models;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Players {
/*
* am definit id-ul ca fiind cheia primara a tabelului 'players'
* */
    @Id
    Integer id;
    String name;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Players() {
    }
    public Players(String newName)
    {
        this.name=newName;
    }
}
