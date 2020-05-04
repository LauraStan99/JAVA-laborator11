package com.example.lab11.controller;

import com.example.lab11.exception.PlayerException;
import com.example.lab11.PlayerRepository;
import com.example.lab11.models.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlayerController {

 /*Autowired: injecția de dependență de Spring .Aceasta este o alternativă la adnotarea Java EE Inject.
 */
    @Autowired
    PlayerRepository playerRepository;

/*Aceasta metoda foloseste o mapare de tipul GET
* Folosindu-ne de obiectul de tip PlayerRepository si de metoda findAll() ,
*  aceasta metoda intoarce toate datele existenta in tabela 'players'
* */
    @GetMapping("/players")
    public List<Players> getPlayers() {
        return playerRepository.findAll();
    }
/*
*avand valorea setata ( /players), fiind o mapare de tipul POST,  si ca body un obiect de tipul Players pe
*  care ulterior cu ajutorul obiectului de tip PlayerRepository il salvam in baza de date , prin apelare metodei
* (save(obiect) ) */
    @PostMapping("/players")
    public Players createPlayer(@Valid @RequestBody Players player) {
        return playerRepository.save(player);
    }


/*
* are ca si valoare /playars/{id} ,oferindui-se informatia necesara pentr a efectua stergerea (id-ul player-ului)
*  fiind o mapare de tipul DELETE , cu ajutorul unui obiect de tipul PlayerRepository cautam in baza de date un obiect in functie de
*  id-ul oferit ca si parametru , in caz de succes se retine obiectul care ulterior este sters ( prin apelarea metodei delete(player) ) ,
*  in caz contrar (nu se gaseste un oobiect in functie de  id) se va genera un mesaj de eroare furnizat de clasa PlayerException care
* trateaza cazul de eroare ( in cazul in care un jucator nu este gasit in baza de date ).
* */
    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long playerId) throws PlayerException {
        Players player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerException(playerId));

        playerRepository.delete(player);

        return ResponseEntity.ok().build();
    }
}
