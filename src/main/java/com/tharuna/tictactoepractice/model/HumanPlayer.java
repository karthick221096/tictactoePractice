package com.tharuna.tictactoepractice.model;

import com.tharuna.tictactoepractice.model.constants.CellState;
import com.tharuna.tictactoepractice.model.constants.PlayerType;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class HumanPlayer extends Player{

    public HumanPlayer(String name, String email, Symbol symbol){
        super(name, email, symbol , PlayerType.HUMAN);
    }
    @Override
    public Move makeMove(Board board){
        Scanner sc = new Scanner(System.in);

        int i, j;

        while (true) {
            System.out.println("Enter row:");
            i = sc.nextInt();

            System.out.println("Enter column:");
            j = sc.nextInt();

            if (isValidIndex(i, j, board)) {
                break;
            }

            System.out.println("Invalid index. Try again.");
        }

        Cell cell = board.getBoard().get(i).get(j);

        cell.setPlayer(this);
        cell.setState(CellState.OCCUPIED);

        return new Move(cell);
    }

    private boolean isValidIndex(int i, int j , Board board){
        int size = board.getBoard().size();

        if(!(i >= 0 && j >= 0 &&
                i < size &&
                j < size)){
            return false;
        }

        return board.getBoard().get(i).get(j).getState() == CellState.EMPTY;
    }

}
