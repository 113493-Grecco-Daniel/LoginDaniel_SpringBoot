package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player> players = playerService.getPlayers();
        return ResponseEntity.ok(players);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id){
        Player player=playerService.getPlayerById(id);
        return ResponseEntity.ok(player);

    }

    @PostMapping("/")
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player) {
        Player playerSaved= playerService.savePlayer(player);
        if(Objects.isNull(playerSaved)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email already exists");
        }else {
            return ResponseEntity.ok(playerSaved);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deletePlayerByID(@PathVariable Long id){
        boolean result= playerService.deletePlayer(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Boolean>deleteAllPlayer(){
        boolean result= playerService.deleteAllPlayers();
        return ResponseEntity.ok(result);
    }


    @PutMapping("/")
    public ResponseEntity<Player>upDatePlayer(@RequestBody Player player){

        Player playerUpdated= playerService.upDatePlayer(player);
        if(Objects.isNull(playerUpdated)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puede actualizar un jugador con esos datos, verifique e intentelo nuevamente");
        }else {
            return ResponseEntity.ok(playerUpdated);
        }

    }




}
