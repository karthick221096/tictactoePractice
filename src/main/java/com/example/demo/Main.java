package com.example.demo;

import com.example.demo.controller.GameController;
import com.example.demo.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int currentPlayerIndex = 0;



        System.out.println("Welcome to the TICTACTOE");
        System.out.println("please enter the board size");
        int boardSize = scanner.nextInt();
        GameController gameController = new GameController(boardSize);
        List<Player> players = gameController.getPlayers(boardSize-1);


        Game game = gameController.startGame(players, boardSize);
        gameController.printBoard(game.getBoard().getBoard());

        while(true){
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("Turn: " + currentPlayer.getName() + ", Type: " + currentPlayer.getPlayerType());
            Board board = game.getBoard();
            Move move = gameController.createMove(currentPlayer,board,game);
            if(currentPlayer.getPlayerType().equals(PlayerType.HUMAN)){
                System.out.println("do you want to undo press 1 for undo 0 for not");
                int undo = scanner.nextInt();
                if(undo == 1){
                    gameController.undoMove(game,move);
                    continue;
                }
            }

            gameController.printBoard(board.getBoard());

            if(gameController.checkWin(board, move)){
                System.out.println(currentPlayer.getName()+" won");
                break;
            }
            currentPlayerIndex = (currentPlayerIndex+1 ) % players.size();
        }

    }
}
