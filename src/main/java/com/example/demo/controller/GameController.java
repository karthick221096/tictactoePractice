package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.BoardService;
import com.example.demo.service.GameService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameController {

    private GameService gameService;
    private BoardService boardService;
    private Scanner scanner;


    public GameController(int size) {
        this.scanner = new Scanner(System.in);
        this.gameService = new GameService(size);
        this.boardService = new BoardService();
    }

    public List<Player> getPlayers(int playerCount) {
        List<Player> players = new ArrayList<>();

        System.out.println("do you want a bot player? (Y/N)");
        String input = scanner.next().toLowerCase();
        if(input.equals("y")){
            Bot bot = new Bot("bot","O",BotDifficultyLevel.EASY);
            players.add(bot);
            playerCount--;
        }

        for (int i = 0; i < playerCount; i++) {
            System.out.println("please enter player name");
            String playerName = scanner.next();
            System.out.println("please enter symbol for " + playerName);
            String symbol = scanner.next();

            players.add(new Player(playerName,PlayerType.HUMAN,symbol));
        }

        Collections.shuffle(players);

        for (Player p : players) {
            System.out.println("Player: " + p.getName() + ", Symbol: " + p.getSymbol());
        }
        return players;
    }

    public Game startGame(List<Player> players,
                          int boardSize) {
        return new Game(players, boardSize);
    }

    public Move createMove(Player player, Board board, Game game) {
        switch (player.getPlayerType()) {
            case HUMAN:
                return gameService.createMove(player, board, game);
            case BOT:
                return gameService.createMove(player, game);
            default:
                throw new IllegalArgumentException("Unknown player type: " + player.getPlayerType());
        }
    }

    public boolean checkWin(Board board, Move move) {
        return gameService.isWinMove(board, move);
    }
    public void undoMove(Game game,Move move) {
        gameService.undoMove(game,move);
    }

    public void printBoard(List<List<Cell>> board) {
        boardService.printBoard(board);
    }

}
