package com.example.demo.strategy;

import com.example.demo.model.Board;
import com.example.demo.model.Game;
import com.example.demo.model.Move;

public interface WinningStrategy {
    boolean checkWin(Board board, Move move);

    void undo(Game game, Move move);
}
