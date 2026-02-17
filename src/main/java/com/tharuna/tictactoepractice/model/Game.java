package com.tharuna.tictactoepractice.model;

import com.tharuna.tictactoepractice.model.constants.CellState;
import com.tharuna.tictactoepractice.model.constants.GameStatus;
import com.tharuna.tictactoepractice.repository.GameRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Game {
    private Board board;
    private List<Player> players;
    private Stack<Move> moves;
    private GameStatus gameStatus;
    private GameRepository gameRepository;


    public Game(int boardSize, List<Player> players){
        this.board = new Board(boardSize);
        this.players = players;
        this.moves = new Stack<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.gameRepository = new GameRepository();
    }

    public boolean checkWinner(Move move, int boardSize){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getCell().getPlayer().getSymbol();

        // -------- ROW CHECK --------
        gameRepository.getRowMap().putIfAbsent(row, new HashMap<>());
        Map<Symbol, Integer> rowSymbolMap = gameRepository.getRowMap().get(row);
        rowSymbolMap.put(symbol, rowSymbolMap.getOrDefault(symbol, 0) + 1);

        if (rowSymbolMap.get(symbol) == boardSize) {
            return true;
        }

        // -------- COLUMN CHECK --------
        gameRepository.getColMap().putIfAbsent(col, new HashMap<>());
        Map<Symbol, Integer> colSymbolMap = gameRepository.getColMap().get(col);
        colSymbolMap.put(symbol, colSymbolMap.getOrDefault(symbol, 0) + 1);

        if (colSymbolMap.get(symbol) == boardSize) {
            return true;
        }

        // -------- LEFT DIAGONAL CHECK --------
        if (row == col) {
            Map<Symbol, Integer> leftDiag = gameRepository.getLeftDiagonalMap();
            leftDiag.put(symbol, leftDiag.getOrDefault(symbol, 0) + 1);

            if (leftDiag.get(symbol) == boardSize) {
                return true;
            }
        }

        // -------- RIGHT DIAGONAL CHECK --------
        if (row + col == boardSize - 1) {
            Map<Symbol, Integer> rightDiag = gameRepository.getRightDiagonalMap();
            rightDiag.put(symbol, rightDiag.getOrDefault(symbol, 0) + 1);

            if (rightDiag.get(symbol) == boardSize) {
                return true;
            }
        }

        return false;
    }

    public void undoGame() {

        if (moves.isEmpty()) {
            System.out.println("No moves to undo");
            return;
        }

        // 1️⃣ Pop last move
        Move lastMove = moves.pop();

        Cell cell = lastMove.getCell();
        int row = cell.getRow();
        int col = cell.getCol();
        Symbol symbol = cell.getPlayer().getSymbol();

        // 2️⃣ Revert board state
        cell.setPlayer(null);
        cell.setState(CellState.EMPTY);

        // 3️⃣ Decrement ROW count
        Map<Symbol, Integer> rowMap = gameRepository.getRowMap().get(row);
        if (rowMap != null && rowMap.containsKey(symbol)) {
            rowMap.put(symbol, rowMap.get(symbol) - 1);
        }

        // 4️⃣ Decrement COLUMN count
        Map<Symbol, Integer> colMap = gameRepository.getColMap().get(col);
        if (colMap != null && colMap.containsKey(symbol)) {
            colMap.put(symbol, colMap.get(symbol) - 1);
        }

        // 5️⃣ Decrement LEFT DIAGONAL (if applicable)
        if (row == col) {
            Map<Symbol, Integer> leftDiag = gameRepository.getLeftDiagonalMap();
            leftDiag.put(symbol, leftDiag.get(symbol) - 1);
        }

        // 6️⃣ Decrement RIGHT DIAGONAL (if applicable)
        if (row + col == board.getSize() - 1) {
            Map<Symbol, Integer> rightDiag = gameRepository.getRightDiagonalMap();
            rightDiag.put(symbol, rightDiag.get(symbol) - 1);
        }

        // 7️⃣ Reset game status
        gameStatus = GameStatus.IN_PROGRESS;

        System.out.println("Undo successful");
    }
}
