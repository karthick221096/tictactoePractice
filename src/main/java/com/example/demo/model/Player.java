package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Player {
    private String name;
    private PlayerType playerType;
    private String symbol;

    public Player() {
        this.playerType = PlayerType.HUMAN;
    }
}
