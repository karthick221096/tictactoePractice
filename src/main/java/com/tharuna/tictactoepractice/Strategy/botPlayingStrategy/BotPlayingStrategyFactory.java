package com.tharuna.tictactoepractice.Strategy.botPlayingStrategy;

import com.tharuna.tictactoepractice.model.constants.BotPlayingStrategyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotPlayingStrategyLevel botPlayingStrategyLevel){

        if (botPlayingStrategyLevel == null) {
            throw new IllegalArgumentException("BotPlayingStrategyLevel cannot be null");
        }

        return switch (botPlayingStrategyLevel){
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
        };
    }
}
