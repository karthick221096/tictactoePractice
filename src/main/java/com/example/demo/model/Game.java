package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Game {
    private List<Player> players; // needed
    private int boardSize; // needed
    private Player winner;
    private GameState gameState;
    private List<Move> moves;// needed
    private Board board;

    public Game(List<Player> players,int boardSize) {
        this.players = players;
        this.board = new Board(boardSize);
        this.gameState = GameState.INPROGRESS;
        this.moves = new ArrayList<>();
        this.boardSize = board.getSize();
    }

}
