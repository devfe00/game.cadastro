// package com.uolhost.challenge.repository;

// import com.uolhost.challenge.model.Player;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import static org.assertj.core.api.Assertions.assertThat;

// @DataJpaTest
// public class PlayerRepositoryTest {

//     @Autowired
//     private PlayerRepository playerRepository;

//     @Test
//     public void testSavePlayer() {
//         Player player = new Player();
//         player.setName("Tony Stark");
//         player.setEmail("ironman@starkindustries.com");
//         player.setPhone("11999999999");
//         player.setCodename("Homem de Ferro");
//         player.setSourceList("vingadores");

//         Player savedPlayer = playerRepository.save(player);

//         assertThat(savedPlayer).isNotNull();
//         assertThat(savedPlayer.getId()).isNotNull();
//         assertThat(savedPlayer.getCodename()).isEqualTo("Homem de Ferro");
//     }
// }
