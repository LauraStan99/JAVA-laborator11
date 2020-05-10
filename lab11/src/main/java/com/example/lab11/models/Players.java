package com.example.lab11.models;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Players {
/*
* am definit id-ul ca fiind cheia primara a tabelului 'players'
* */
    @Id
    Long id;
    String name;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
