package ar.edu.utn.frc.tup.lciii.services.imp;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImp implements PlayerService {
    private final Logger log = LoggerFactory.getLogger(PlayerServiceImp.class);

    @Autowired
    private PlayerJpaRepository playerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelMapper mergerMapper;

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        List<PlayerEntity> playersEntities = playerRepository.findAll();

        if (Objects.isNull(playersEntities.get(0).getUserName())) {
            throw new EntityNotFoundException();
        } else {
            for (PlayerEntity playerEntity : playersEntities) {
                Player player = modelMapper.map(playerEntity, Player.class);
                players.add(player);
            }
            return players;
        }
    }

    @Override
    public Player getPlayerById(Long id) {

        PlayerEntity playerEntity = playerRepository.getReferenceById(id);
        if (Objects.isNull(playerEntity.getUserName())) {
            throw new EntityNotFoundException();
        } else {
            Player player = modelMapper.map(playerEntity, Player.class);
            return player;
        }
    }

    @Override
    public Player savePlayer(Player player) {
        Optional<PlayerEntity> playerEntityOptional = playerRepository.findByUserNameOrEmail(
                player.getUserName(), player.getEmail());
        if (playerEntityOptional.isEmpty()) {
            PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
            PlayerEntity playerEntitySaved = playerRepository.save(playerEntity);

            return modelMapper.map(playerEntitySaved, Player.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean deletePlayer(@PathVariable Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deleteAllPlayers() {
        if (playerRepository.findAll().size() > 0) {
            playerRepository.deleteAll();
            return true;
        } else {
            return false;
        }

    }
     @Override
    public Player upDatePlayer(Player player) {
    if(player.getId() ==null){
        log.warn("debe ingresar un id");

        return null;
    }
    else if(!playerRepository.existsById(player.getId()))
    {
        log.warn("estas tratando de actualizar un Player que no existe");

        return null;
    }else{
        PlayerEntity playerEntity = mergerMapper.map(player, PlayerEntity.class);
        PlayerEntity playerEntitySaved = playerRepository.save(playerEntity);

        return modelMapper.map(playerEntitySaved, Player.class);
    }

    }
}