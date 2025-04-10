package com.uolhost.challenge.controller;

import com.uolhost.challenge.model.Player;
import com.uolhost.challenge.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PlayerController {

    private final PlayerService playerService;


    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("player")) {
            model.addAttribute("player", new Player());
        }
        return "index";
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("players", playerService.getAllPlayers());
        return "report";
    }

    @PostMapping("/register")
    public String registerPlayer(@Valid @ModelAttribute("player") Player player,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "index";
        }
    
        try {

            Player registeredPlayer = playerService.registerPlayer(player, player.getSourceList());
    
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("player", registeredPlayer);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("player", player);
        }
    
        return "redirect:/";
    }
    }
    
    