package com.uolhost.challenge.repository;

import com.uolhost.challenge.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    
    Optional<Player> findByCodename(String codename);
    
    List<Player> findBySourceList(String sourceList);
    
    boolean existsByCodenameAndSourceList(String codename, String sourceList);
}