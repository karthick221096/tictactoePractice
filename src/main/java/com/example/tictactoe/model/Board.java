package com.example.tictactoe.model;

import com.example.tictactoe.model.constant.CellState;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Board {
    private int size;
    private int capacity;
    private List<List<Cell>> board;

    public Board(int size){
        this.size =size;
        this.capacity = 0;
        this.board = new ArrayList<>();
        for(int i = 0;i<size ; i++){
            ArrayList<Cell> row = new ArrayList<>();
            for (int j=0;j<size;j++){
                Cell cell = new Cell(i,j);
                cell.setCellState(CellState.EMPTY);
                row.add(cell);
            }
            this.board.add(row);
        }

    }

    public void display(){
        for(int i = 0;i<size;i++){
            for(int j = 0;j<size;j++){
                Cell cell = board.get(i).get(j);
                if (cell.getPlayer()!=null){
                    System.out.print(cell.getPlayer().getSymbol()+" ");
                }
                else{
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }
}
