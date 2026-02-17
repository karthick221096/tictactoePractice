package com.tharuna.tictactoepractice.Strategy.botPlayingStrategy;

import com.tharuna.tictactoepractice.model.Board;
import com.tharuna.tictactoepractice.model.Cell;
import com.tharuna.tictactoepractice.model.Move;
import com.tharuna.tictactoepractice.model.Player;
import com.tharuna.tictactoepractice.model.constants.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board, Player player) {
         for(List<Cell> rowCell: board.getBoard()){
             for(Cell cell : rowCell){
                 if(cell.getState().equals(CellState.EMPTY)){
                     cell.setState(CellState.OCCUPIED);
                     cell.setPlayer(player);
                     return new Move(cell);
                 }
             }
         }
         return null;
    }
}
