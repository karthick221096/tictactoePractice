package com.tharuna.tictactoepractice.repository;

import com.tharuna.tictactoepractice.model.Symbol;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GameRepository {
    private Map<Integer, Map<Symbol, Integer>> ColMap;
    private Map<Integer, Map<Symbol, Integer>> RowMap;
    private Map<Symbol, Integer> leftDiagonalMap;
    private Map<Symbol, Integer> rightDiagonalMap;

    public GameRepository() {
        ColMap = new HashMap<>();
        RowMap = new HashMap<>();
        leftDiagonalMap = new HashMap<>();
        rightDiagonalMap = new HashMap<>();
    }
}
