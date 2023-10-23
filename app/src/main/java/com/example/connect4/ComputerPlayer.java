package com.example.connect4;
import java.util.ArrayList;

public class ComputerPlayer extends Player {
    //fields


    // constructor
    public ComputerPlayer(Cell counter, String playerName){
        super(counter, playerName);
    }

    //method


    public int computerInputAI(BoardConnection currentConnect, Board currentBoard){
        // TO DO - GUARD AGAINST full columns
        // check existing empty columns
        ArrayList<Integer> emptyCols = currentBoard.returnEmptyColumns();

        //initialise column variable to be dropped in in centre of board, if empty
        int column = 5;

        boolean isChosenColEmpty = currentBoard.searchEmptyColumns(4);
        if(currentBoard.isColumnFull(4)== true){
            column = 1;
        }


        // establish data on current board connection
        int startCol = currentConnect.returnStartColumn(); // count from left, zero indexed
        int startRow = currentConnect.returnStartRow(); // count from top, zero indexed
        int connectionLength = currentConnect.returnConnectionLength();
        String connectionType = currentConnect.returnConnectionType();

        //UNIT TESTS
        System.out.println(connectionLength);
        System.out.println(connectionType);


        // if current best connection is vertical
        if(connectionType.equals("vertical")){

            // put counter on top of vertical connection, if empty
            column = startCol;
            isChosenColEmpty = currentBoard.searchEmptyColumns(startCol);
            if(isChosenColEmpty== false){
                column = 1;
            }

        }
        // if current best connection is horizontal
        if(connectionType.equals("horizontal")){
            int endCol1 = startCol + (connectionLength-1);
            int endCol2 = startCol - (connectionLength-1);
            int endRow = startRow;

            /* guard against array out of bounds, assume connection moving towards right and place on right hand side
             */
            if (startCol < 6 && startCol > 3){
                column = startCol+1;
                isChosenColEmpty = currentBoard.searchEmptyColumns(column);
                if(isChosenColEmpty== false){
                    column = 1;
                }

            }
            /* guard against array out of bounds, assume connection moving towards left and place on left hand side
             */
            if(startCol < 4 && startCol > 0){
                column = startCol-1;
                isChosenColEmpty = currentBoard.searchEmptyColumns(column);
                if(isChosenColEmpty== false){
                    column = 1;
                }
            }
        }
        // otherwise leave computer input to drop in centre


        return column;
    }




}
