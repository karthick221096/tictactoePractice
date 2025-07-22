package com.example.demo.strategy;

import com.example.demo.model.Board;
import com.example.demo.model.Game;
import com.example.demo.model.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinnerChecker implements WinningStrategy {
    private Map<String,Integer> leftDaigonal;
    private Map<String,Integer> rightDaigonal;
    private List<Map<String,Integer>> rowSymbolCount;
    private List<Map<String,Integer>> columnSymbolCount;

    public WinnerChecker(int size) {
        this.rowSymbolCount = new ArrayList<>();
        this.columnSymbolCount = new ArrayList<>();
        this.leftDaigonal = new HashMap<>();
        this.rightDaigonal = new HashMap<>();
        for(int i = 0; i < size; i++) {
            rowSymbolCount.add(new HashMap<>());
            columnSymbolCount.add(new HashMap<>());
        }
    }



    @Override
    public boolean checkWin(Board board, Move move) {

        int boardSize = board.getSize();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        String symbol = move.getPlayer().getSymbol();

        Map<String,Integer> currentRow = rowSymbolCount.get(row);
        currentRow.put(symbol,currentRow.getOrDefault(symbol,0)+1);
        Map<String,Integer> currentCol = columnSymbolCount.get(col);
        currentCol.put(symbol,currentCol.getOrDefault(symbol,0)+1);

        if(currentRow.get(symbol)==boardSize || currentCol.get(symbol)==boardSize) {
            return true;
        }

        if(row==col){
            leftDaigonal.put(symbol,leftDaigonal.getOrDefault(symbol,0)+1);
            if(leftDaigonal.get(symbol)==boardSize){
                return true;
            }
        }

        if(row+col==boardSize-1){
            rightDaigonal.put(symbol,rightDaigonal.getOrDefault(symbol,0)+1);
            if(rightDaigonal.get(symbol)==boardSize){
                return true;
            }
        }

        return false;
    }

    @Override
    public void undo(Game game, Move move) {
        List<Move> moves = game.getMoves();
        int boardSize = game.getBoardSize();
        if(!moves.isEmpty()){
            moves.remove(moves.size()-1);
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        String symbol = move.getPlayer().getSymbol();
        Map<String,Integer> currentRow = rowSymbolCount.get(row);
        if(currentRow.containsKey(symbol)){
            currentRow.put(symbol,currentRow.get(symbol)-1);
            if (currentRow.get(symbol) == 0) {
                currentRow.remove(symbol);
            }
        }
        Map<String,Integer> currentCol = columnSymbolCount.get(col);
        if(currentCol.containsKey(symbol)){
            currentCol.put(symbol,currentCol.get(symbol)-1);
            if (currentCol.get(symbol) == 0) {
                currentCol.remove(symbol);
            }
        }
        if(row == col && leftDaigonal.containsKey(symbol)){
            leftDaigonal.put(symbol,leftDaigonal.get(symbol)-1);
        }
        if(row+col == boardSize-1 && rightDaigonal.containsKey(symbol)){
            rightDaigonal.put(symbol,rightDaigonal.get(symbol)-1);
        }
    }
}
