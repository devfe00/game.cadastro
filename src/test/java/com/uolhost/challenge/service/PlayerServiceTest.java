package com.uolhost.challenge.service;

import com.uolhost.challenge.model.Player;
import com.uolhost.challenge.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private CodenameService codenameService;

    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterPlayerSuccessfully() {
        Player player = new Player();
        player.setName("Teste");
        player.setEmail("teste@example.com");
        
        String sourceList = "vingadores";
        String codename = "Homem de Ferro";
        
        when(codenameService.getAvailableCodename(sourceList)).thenReturn(Optional.of(codename));
        when(playerRepository.save(any(Player.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        Player registeredPlayer = playerService.registerPlayer(player, sourceList);
        
        assertNotNull(registeredPlayer);
        assertEquals(codename, registeredPlayer.getCodename());
        assertEquals(sourceList, registeredPlayer.getSourceList());
    }

    @Test
    void shouldThrowExceptionWhenNoCodenameAvailable() {
        Player player = new Player();
        player.setName("Teste");
        player.setEmail("teste@example.com");
        
        String sourceList = "liga_da_justica";
        
        when(codenameService.getAvailableCodename(sourceList)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            playerService.registerPlayer(player, sourceList);
        });
        
        assertTrue(exception.getMessage().contains("Não há codinomes disponíveis"));
    }
}