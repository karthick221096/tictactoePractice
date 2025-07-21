package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Bot extends Player{

    private BotDifficultyLevel difficulty;

    public Bot() {
        super();
        this.difficulty = BotDifficultyLevel.EASY;
    }
}
