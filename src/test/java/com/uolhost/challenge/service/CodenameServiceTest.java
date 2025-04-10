package com.uolhost.challenge.service;

import com.uolhost.challenge.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CodenameServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private CodenameService codenameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAvailableCodename() {
        when(playerRepository.existsByCodenameAndSourceList(anyString(), anyString())).thenReturn(false);
        
        Optional<String> codename = codenameService.getAvailableCodename("vingadores");
        
        assertTrue(codename.isPresent(), "Codename should be available");
    }

    @Test
    void shouldReturnEmptyWhenAllCodenamesAreUsed() {
        when(playerRepository.existsByCodenameAndSourceList(anyString(), anyString())).thenReturn(true);

        Optional<String> codename = codenameService.getAvailableCodename("liga_da_justica");
        

        assertTrue(codename.isEmpty(), "Codename should not be available");
    }
}
