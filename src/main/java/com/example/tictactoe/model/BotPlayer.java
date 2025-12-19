package com.example.tictactoe.model;

import com.example.tictactoe.model.constant.CellState;
import com.example.tictactoe.model.constant.PlayerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotPlayer extends Player{
    public BotPlayer(){
        this.playerType = PlayerType.BOT;
        this.setName("BOT");
    }

    @Override
    public Board makeMove(Board board, int ignoredRow, int ignoredCol) {
        int size = board.getSize();
        for (int i = 0;i<size;i++){
            for(int j=0 ;j<size;j++){
                Cell cell = board.getBoard().get(i).get(j);
                if(cell.getCellState().equals(CellState.EMPTY)){
                    cell.setCellState(CellState.OCCUPIED);
                    cell.setPlayer(this);
                    return board;
                }
            }
        }
        return board;
    }
}
