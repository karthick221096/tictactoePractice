package com.example.tictactoe.model;

import com.example.tictactoe.model.constant.CellState;
import com.example.tictactoe.model.constant.PlayerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumanPlayer extends Player{

    public HumanPlayer (){
        this.playerType = PlayerType.HUMAN;
    }

    @Override
    public Board makeMove(Board board, int row, int col){
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.OCCUPIED);
        cell.setPlayer(this);
        return board;
    }
}
