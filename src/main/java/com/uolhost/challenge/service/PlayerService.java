package com.uolhost.challenge.service;

import com.uolhost.challenge.model.Player;
import com.uolhost.challenge.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final CodenameService codenameService;

    public PlayerService(PlayerRepository playerRepository, CodenameService codenameService) {
        this.playerRepository = playerRepository;
        this.codenameService = codenameService;
    }

    public Player registerPlayer(Player player, String sourceList) {
        String codename = codenameService.getAvailableCodename(sourceList)
                .orElseThrow(() -> new RuntimeException("Não há codinomes disponíveis"));

        player.setCodename(codename);
        player.setSourceList(sourceList);

        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
