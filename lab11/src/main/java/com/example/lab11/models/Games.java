package com.example.lab11.models;

import java.util.List;

public class Games {
    /*
    * clasa contine o lista cu players
    */
    List<Players> playersList;

   public  Games(List<Players>list)
    {
        this.playersList=list;
    }

    public void setPlayersList(List<Players> playersList) {
        this.playersList = playersList;
    }

    public List<Players> getPlayersList() {
        return playersList;
    }
}
