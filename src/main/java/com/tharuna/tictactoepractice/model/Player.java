package com.tharuna.tictactoepractice.model;

import com.tharuna.tictactoepractice.model.constants.PlayerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Player {
    private String name;
    private String email;
    private Symbol symbol;
    private PlayerType playerType;

    public Player(String name, String email, Symbol symbol, PlayerType playerType) {
        this.email = email;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public abstract Move makeMove(Board board);

}
