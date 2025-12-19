package com.example.tictactoe.model;

import com.example.tictactoe.model.constant.CellState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Player player;
    public Cell(int x, int y){
        this.row = x;
        this.col = y;
    }
}
