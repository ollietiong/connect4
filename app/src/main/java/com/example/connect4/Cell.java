package com.example.connect4;

public enum Cell {
    EMPTY(' '), PLAYER_1('r'), PLAYER_2('y');

    private final char cellState;

    Cell(char cellState){
        this.cellState = cellState;
    }

    public char getCellState(){
        return cellState;
    }


}

