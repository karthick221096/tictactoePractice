package com.tharuna.tictactoepractice.model;

import com.tharuna.tictactoepractice.model.constants.CellState;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {

    private int size;

    private List<List<Cell>> board;

    public Board(int size){
        this.size = size;
        this.board = new ArrayList<>(size);
        for(int i=0; i<size ; i++){
            List<Cell> rowCell = new ArrayList<>(size);
            for(int j = 0; j<size ; j++){
                rowCell.add(j,new Cell(i,j));
            }
            board.add(i,rowCell);
        }
    }


    public void printBoard(){
        for(int i = 0; i< size ; i++){
            for(int j = 0; j< size ; j++){
                Cell cell = board.get(i).get(j);
                if(cell.getState().equals(CellState.OCCUPIED)){
                    System.out.print("| " + cell.getPlayer().getSymbol().getSymbol() +" |");
                }
                else{
                    System.out.print("| - |");
                }

            }
            System.out.println();
        }
    }

}
