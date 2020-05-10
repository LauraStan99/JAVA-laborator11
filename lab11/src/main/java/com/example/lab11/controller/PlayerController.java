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
     * avand valorea setata ( /players), fiind o mapare de tipul POST,  si ca body un obiect de tipul Players pe
     *  care ulterior cu ajutorul obiectului de tip PlayerRepository il salvam in baza de date , prin apelare metodei
     * (save(obiect) ) */
    @PostMapping("/players")
    public Players createPlayer(@Valid @RequestBody Players player) {
        return playerRepository.save(player);
    }

    /*
     * metodei i se ofera informatia necesara pentru a efectua stergerea (id-ul player-ului)
     *  fiind o mapare de tipul DELETE , cu ajutorul unui obiect de tipul PlayerRepository cautam in baza de date un obiect in functie de
     *  id-ul oferit ca si parametru , in caz de succes se retine obiectul care ulterior este sters ( prin apelarea metodei delete(player) ) ,
     *  in caz contrar (nu se gaseste un obiect in functie de  id) se va genera un mesaj de eroare furnizat de clasa PlayerException care
     * trateaza cazul de eroare ( in cazul in care un jucator nu este gasit in baza de date ).
     * */
    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") Long playerId) throws PlayerException {
        Players player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerException(playerId));

        playerRepository.delete(player);
        return ResponseEntity.ok().build();
    }

    /*
    * metodei i se ofera informatia necesara pentru a se realiza update-ul (cautand in baza de date jucatorul in functie de id), avand
    * in body obiectul de tip Players care urmeaza a fi pus in baza de date (in caz ca s-a gasit locul de inlocuit dupa id)
    * fiind o mapare de tipul PUT
    * cu ajutorul unui obiect de tipul PlayerRepository si a functiei findById cautam obiectul in baza de date in functie de id-ul
    * oferit ca si parametru , in cazul in care nu este gasit se va genera un mesaj de eroare furnizat de clasa PlayerException care
    * trateaza cazul de eroare ( in cazul in care un jucator nu este gasit in baza de date ).
    * in caz contrar cu ajutorul metodelor set se seteaza noile informatii (ale obiectului din body si care vor fi preluate de
    * metode get) si se salveaza noul obiect
    */
    @PutMapping("/players/{id}")
    public Players updatePlayer(@PathVariable(value = "id") Long playerId,
                           @Valid @RequestBody Players playersDetails) throws PlayerException {

        Players player = playerRepository.findById(playerId)
                .orElseThrow(()-> new PlayerException(playerId));
        player.setId(playersDetails.getId());
        player.setName(playersDetails.getName());

        Players updatedPlayer = playerRepository.save(player);

        return updatedPlayer;
    }
}
