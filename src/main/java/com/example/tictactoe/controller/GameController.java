package com.example.tictactoe.controller;

import com.example.tictactoe.model.*;
import com.example.tictactoe.model.constant.CellState;
import com.example.tictactoe.model.constant.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Starting TICTACTOE");
        System.out.println("Enter the no of player");

        int noOfPlayer = scanner.nextInt();
        int boardSize = noOfPlayer+1;

        List<Player> playerList = new ArrayList<>();

        System.out.println("Are we going to have bot player : enter 1 for bot 0 for no bot");
        int botFlag = scanner.nextInt();

        if (botFlag == 1){
            noOfPlayer--;
        }

        // get the list of players
        for (int i = 0 ;i<noOfPlayer ; i++){
            Player player = new HumanPlayer();
            System.out.println("enter name of human Player");
            String name = scanner.nextLine();
            player.setName(name);
            System.out.println("enter symbol for player" + name);
            String symbol = scanner.nextLine();
            player.setSymbol(symbol);
            playerList.add(player);
        }

        if (botFlag == 1){
            Player botPlayer = new BotPlayer();
            botPlayer.setSymbol("o");
            botPlayer.setName("BOT");
            playerList.add(botPlayer);
            noOfPlayer++;
        }

        Board board = new Board(boardSize);
        board.display();
        int turnCounter = 0;

        while (true) {

            // -------- Draw Check --------
            if (board.getCapacity() == boardSize * boardSize) {
                System.out.println("Game is draw");
                break;
            }

            Player currPlayer = playerList.get(turnCounter % playerList.size());

            if (currPlayer.getPlayerType() == PlayerType.HUMAN) {

                System.out.println("It's " + currPlayer.getName() + "'s turn");
                System.out.println("Enter row (0-2): ");
                int row = scanner.nextInt();
                System.out.println("Enter col (0-2): ");
                int col = scanner.nextInt();
//              check if already occupied
                if(board.getBoard().get(row).get(col).getCellState() == CellState.OCCUPIED){
                    System.out.println("The cell is already occupied choose new cell");
                    continue;
                }
                currPlayer.makeMove(board,row,col);
            }
            else{
                System.out.println("Its Bot's turn bot is playing Now");
                currPlayer.makeMove(board,0,0);
            }

            board.setCapacity(board.getCapacity() + 1);
            board.display();

            // -------- Winner Check --------
            if (checkWinner(board.getBoard(), currPlayer)) {
                System.out.println("Player " + currPlayer.getName() + " WON!");
                break;
            }

            turnCounter++;
        }
    }

    private static boolean checkWinner(List<List<Cell>> board, Player player) {

        String symbol = player.getSymbol();
        int size = board.size();

        // -------- Check Rows --------
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                Cell cell = board.get(i).get(j);
                if (cell.getCellState() != CellState.OCCUPIED ||
                    !cell.getPlayer().getSymbol().equals(symbol)) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // -------- Check Columns --------
        for (int j = 0; j < size; j++) {
            boolean win = true;
            for (int i = 0; i < size; i++) {
                Cell cell = board.get(i).get(j);
                if (cell.getCellState() != CellState.OCCUPIED ||
                    !cell.getPlayer().getSymbol().equals(symbol)) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Main Diagonal
        boolean win = true;
        for (int i = 0; i < size; i++) {
            Cell cell = board.get(i).get(i);
            if (cell.getCellState() != CellState.OCCUPIED ||
                !cell.getPlayer().getSymbol().equals(symbol)) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Anti-Diagonal
        win = true;
        for (int i = 0; i < size; i++) {
            Cell cell = board.get(i).get(size - 1 - i);
            if (cell.getCellState() != CellState.OCCUPIED ||
                !cell.getPlayer().getSymbol().equals(symbol)) {
                win = false;
                break;
            }
        }
        return win;
    }
}
