package com.example.demo.service;


import com.example.demo.exception.InvalidMoveException;
import com.example.demo.model.*;
import com.example.demo.strategy.BotPlayingStrategyFactory;
import com.example.demo.strategy.WinnerChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameService {

    private final Scanner scanner = new Scanner(System.in);
    private WinnerChecker winnerChecker;

    public GameService(int size) {
        this.winnerChecker = new WinnerChecker(size);
    }


    public Move createMove(Player player, Board board,Game game) {

        System.out.println("Its "+player.getName()+" turn please make a move");
        System.out.println("please enter row where you want to make a move");
        int row = scanner.nextInt();
        System.out.println("please enter column where you want to make a move");
        int column = scanner.nextInt();

        if(!(board.getBoard().get(row).get(column).getCellState() ==CellState.EMPTY)){
            throw new InvalidMoveException();
        }

        Cell cellToUpdate = board.getBoard().get(row).get(column);

        cellToUpdate.setPlayer(player);
        cellToUpdate.setCellState(CellState.FULL);

        Move move = new Move(cellToUpdate,player);
        game.getMoves().add(move);

        return move;
    }

    public Move createMove(Player player,Game game) {
        Move move = BotPlayingStrategyFactory.getBotPlayingStrategy(BotDifficultyLevel.EASY)
                .createMove(player, game);
        Cell cell = move.getCell();
        cell.setPlayer(player);
        cell.setCellState(CellState.FULL);
        game.getMoves().add(move);
        return move;
    }

    public boolean isWinMove(Board board, Move move) {
        return winnerChecker.checkWin(board, move);
    }

    public void undoMove(Game game,Move move) {
        winnerChecker.undo(game, move);
    }
}
