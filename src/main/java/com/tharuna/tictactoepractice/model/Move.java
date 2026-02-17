package com.tharuna.tictactoepractice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
    private Cell cell;

    public Move(Cell cell){
        this.cell = cell;
    }
}
