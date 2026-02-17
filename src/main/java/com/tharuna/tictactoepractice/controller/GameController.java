package com.tharuna.tictactoepractice.controller;

import com.tharuna.tictactoepractice.model.*;
import com.tharuna.tictactoepractice.model.constants.BotPlayingStrategyLevel;
import com.tharuna.tictactoepractice.model.constants.GameStatus;
import com.tharuna.tictactoepractice.model.constants.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {


    public static void main(String[] args) {
        System.out.println("welcome to tictacToe");

        System.out.println("hardcoding for quickboot up with gate size 3 with default one bot player");
        Scanner sc = new Scanner(System.in);

        int gameSize = 3;
        List<Player> players = new ArrayList<>(gameSize-1);
        players.add(0,new BotPlayer(new Symbol("o"), BotPlayingStrategyLevel.EASY));
        players.add(1,new HumanPlayer("kartthi","k@gmail.com",new Symbol("x")));
        Game game = new Game(gameSize,players);

        int currPlayerIndex = 0;

        game.getBoard().printBoard();

        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)){
            System.out.println("\nCurrent player: "
                    + players.get(currPlayerIndex).getName());

            System.out.println("Press U to undo or press Enter to continue");
            String input = sc.nextLine();

            // ---------------- UNDO ----------------
            if (input.equalsIgnoreCase("u")) {

                if (!game.getMoves().isEmpty()) {

                    game.undoGame();

                    // Move back to previous player
                    currPlayerIndex =
                            (currPlayerIndex - 1 + players.size()) % players.size();

                    game.getBoard().printBoard();
                } else {
                    System.out.println("No moves to undo");
                }

                continue;
            }

            // ---------------- NORMAL MOVE ----------------
            Player currentPlayer = players.get(currPlayerIndex);

            Move move = currentPlayer.makeMove(game.getBoard());

            // Store move in stack
            game.getMoves().push(move);

            // Check Winner
            if (game.checkWinner(move, game.getBoard().getSize())) {

                game.setGameStatus(GameStatus.WIN);

                game.getBoard().printBoard();
                System.out.println("🎉 Winner is: "
                        + currentPlayer.getName());

                break;
            }

            // Check Draw
            if (game.getMoves().size() ==
                    game.getBoard().getSize() * game.getBoard().getSize()) {

                game.setGameStatus(GameStatus.DRAW);

                game.getBoard().printBoard();
                System.out.println("🤝 Game is a DRAW");

                break;
            }

            game.getBoard().printBoard();

            // Switch turn
            currPlayerIndex =
                    (currPlayerIndex + 1) % players.size();
        }

        System.out.println("Game Over");

    }
}
