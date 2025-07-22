package com.example.demo.service;

import com.example.demo.model.Cell;
import com.example.demo.model.CellState;

import java.util.List;

public class BoardService {

    public void printBoard(List<List<Cell>> board) {
        for(List<Cell> row : board){
            for(Cell cell : row){
                if(cell.getCellState() == CellState.FULL){
                    System.out.print(" | "+cell.getPlayer().getSymbol()+" | ");
                }
                else if(cell.getCellState() == CellState.EMPTY){
                    System.out.print(" | - | ");
                }
            }
            System.out.println();
        }
    }
}
