package com.example.demo.strategy;

import com.example.demo.model.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel == BotDifficultyLevel.EASY){
            return new EasyBotPlayingStrategy();
        }
        return new EasyBotPlayingStrategy();
    }
}
