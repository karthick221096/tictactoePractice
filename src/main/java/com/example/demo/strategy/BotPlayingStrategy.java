package com.example.demo.strategy;

import com.example.demo.model.Game;
import com.example.demo.model.Move;
import com.example.demo.model.Player;

public interface BotPlayingStrategy {
    Move createMove(Player player, Game game);
}
