package com.example.lab11.exception;

public class PlayerException extends Exception {
    public PlayerException(Long playerId){
    super(String.format("Jucatorul cu id-ul : '%s' nu a fost gasit !", playerId));
}
}
