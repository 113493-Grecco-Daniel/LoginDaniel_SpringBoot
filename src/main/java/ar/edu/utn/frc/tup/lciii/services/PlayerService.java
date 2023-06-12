package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    List<Player> getPlayers();
    Player getPlayerById(Long id);
    Player savePlayer(Player player);
    boolean deletePlayer(Long id);
    boolean deleteAllPlayers();
    Player upDatePlayer(Player player);

}
