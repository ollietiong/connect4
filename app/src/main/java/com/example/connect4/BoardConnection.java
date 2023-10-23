package com.example.connect4;


public class BoardConnection{
    // fields
    private String connectionType;
    private int connectionLength;
    private int startColumn;
    private int startRow;

    // constructor
    public BoardConnection(){

    }

    // method
    public void updateBoardConnection(String type, int length, int col, int row){
        this.connectionType = type;
        this.connectionLength = length;
        this.startColumn = col;
        this.startRow = row;
    }

    public String returnConnectionType(){
        return connectionType;
    }

    public int returnConnectionLength(){
        return connectionLength;
    }

    public int returnStartColumn(){
        return startColumn;
    }
    public int returnStartRow(){
        return startRow;
    }

}
