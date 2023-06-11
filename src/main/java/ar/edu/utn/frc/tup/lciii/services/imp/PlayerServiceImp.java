package ar.edu.utn.frc.tup.lciii.services.imp;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImp implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Player getPlayerById(Long id) {

        PlayerEntity playerEntity = playerRepository.getReferenceById(id);
        if(Objects.isNull(playerEntity.getUserName()))
        {
            throw new EntityNotFoundException();
        }
        else {
            Player player= modelMapper.map(playerEntity, Player.class);
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


}
