package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Bot extends Player{

    private BotDifficultyLevel difficulty;

    public Bot(String name, String symbol, BotDifficultyLevel difficulty) {
        super(name,PlayerType.BOT,symbol);
        this.difficulty = difficulty;
    }
}
