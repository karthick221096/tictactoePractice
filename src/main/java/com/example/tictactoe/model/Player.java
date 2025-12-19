package com.example.tictactoe.model;

import com.example.tictactoe.model.constant.PlayerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Player {
    private String name;
    public PlayerType playerType;
    private String symbol;

    public abstract Board makeMove(Board board, int row, int col);
}
