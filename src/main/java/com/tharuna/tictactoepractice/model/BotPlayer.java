package com.tharuna.tictactoepractice.model;

import com.tharuna.tictactoepractice.Strategy.botPlayingStrategy.BotPlayingStrategy;
import com.tharuna.tictactoepractice.Strategy.botPlayingStrategy.BotPlayingStrategyFactory;
import com.tharuna.tictactoepractice.model.constants.BotPlayingStrategyLevel;
import com.tharuna.tictactoepractice.model.constants.PlayerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotPlayer extends Player{

    private BotPlayingStrategyLevel botPlayingStrategyLevel;

    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(Symbol symbol, BotPlayingStrategyLevel botPlayingStrategyLevel) {
        super("BotPlayer", "botEmail", symbol, PlayerType.BOT);
        this.botPlayingStrategyLevel = botPlayingStrategyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botPlayingStrategyLevel);
    }

    @Override
    public Move makeMove(Board board){
        return botPlayingStrategy.makeMove(board,this);
    }
}
