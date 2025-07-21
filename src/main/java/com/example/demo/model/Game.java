package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Game {
    private List<Player> players;
    private int boardSize;
    private Player winner;
    private GameState gameState;
    private List<Move> moves;

}
