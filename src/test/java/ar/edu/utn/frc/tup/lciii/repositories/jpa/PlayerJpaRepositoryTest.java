package ar.edu.utn.frc.tup.lciii.repositories.jpa;


import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PlayerJpaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PlayerJpaRepository playerJpaRepository;
    @Test
    public void findByUserNameOrEmailTest(){ // si coincide el valor explota...
        PlayerEntity playerEntity= new PlayerEntity();
        // playerEntity.setId(1L);
        playerEntity.setEmail("emfrhail@email.com");
        playerEntity.setUserName("Danifgel");
        playerEntity.setPassword("Cordoba+1");
        entityManager.persist(playerEntity);
        entityManager.flush();
        Optional<PlayerEntity> result= playerJpaRepository.findByUserNameOrEmail("Daniel","email@email.com");
        assertEquals("Daniel", result.get().getUserName());
    }
}
