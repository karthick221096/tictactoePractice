package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Player {
    private String name;
    private PlayerType playerType;
    private String symbol;

    public Player(String name,PlayerType playerType, String symbol) {
        this.playerType = playerType;
        this.name = name;
        this.symbol = symbol;
    }
}
