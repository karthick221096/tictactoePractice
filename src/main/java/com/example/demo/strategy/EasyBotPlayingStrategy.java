package com.example.demo.strategy;

import com.example.demo.model.*;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move createMove(Player player, Game game) {
        List<List<Cell>> board = game.getBoard().getBoard();

        for(List<Cell> row : board){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell, player);
                }
            }
        }
        return null;
    }
}
