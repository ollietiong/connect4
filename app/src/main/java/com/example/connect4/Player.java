package com.example.connect4;

public abstract class Player{
    // fields
    private Cell counter;
    private String playerName;


    // constructor
    public Player(Cell counter, String playerName){
        this.counter = counter;
        this.playerName = playerName;

    }
    //methods
    public String getPlayerName(){
        return playerName;
    }
    public Cell getPlayerCell(){
        return counter;
    }


}