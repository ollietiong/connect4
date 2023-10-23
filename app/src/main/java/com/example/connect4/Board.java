package com.example.connect4;

import java.util.ArrayList;

public class Board {

    protected Cell[][] board;

    //constructor
    public Board(){
        board = new Cell[6][7];
    }

    //methods
    public void setUpBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = Cell.EMPTY;
            }
        }
    }

    public Cell getBoardCell(int i, int j){
        return board[i][j];
    }

    public void addCounter(Cell player, int position){
        boolean placed = false;

        for(int i = board.length -1; i>=0; i--){
            if(!placed){
                if(board[i][position] != player && board[i][position] != Cell.EMPTY){
                    //skip
                }
                else if (board[i][position] != player){
                    board[i][position] = player; // changes matrix position to current player adding counter
                    placed = true;

                }
            }
        }
    }
    // check to see if column is full
    public boolean isColumnFull(int column){
        if(board[0][column]==Cell.EMPTY){
            return false;
        }
        else{
            return true;
        }
    }
    // method provides an arraylist of empty columns
    public ArrayList<Integer> returnEmptyColumns(){
        ArrayList<Integer> emptyColumns = new ArrayList<Integer>();
        for(int i=0; i < 7; i++) {
            if (board[0][i] == Cell.EMPTY) {
                emptyColumns.add(i);
            }
        }

        return emptyColumns;
    }

    public boolean searchEmptyColumns(int col){
        ArrayList<Integer> emptyColumns = returnEmptyColumns();
        int length = emptyColumns.size();
        boolean found = false;
        for(int i =0; i < length; i++){
            if(emptyColumns.get(i) == col){
                found = true;
            }
        }
        return found;
    }


    public BoardConnection checkBoard(Cell player) {

        BoardConnection horizConnect = new BoardConnection();
        int count = 0;
        // check horizontal
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == player) {
                    count = count + 1;
                    horizConnect.updateBoardConnection("horizontal", count, j, i);
                    if (count >= 4) {
                        return horizConnect;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        // check vertical
        count = 0;
        BoardConnection vertConnect = new BoardConnection();
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == player) {
                    count = count + 1;
                    vertConnect.updateBoardConnection("vertical", count, i, j);

                    if (count >= 4) {
                        return vertConnect;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        // check diagonal, right upwards
        count = 0;
        BoardConnection diagRightConnect = new BoardConnection();
        for (int i = board.length - 1; i > 2; i--) {
            for (int j = 0; j < 4; j++) { // scan across width
                if (board[i][j] == player) {
                    count = count + 1;
                    diagRightConnect.updateBoardConnection("diagonal right", count, j, i);
                    if (board[i - 1][j + 1] == player) {
                        count = count + 1;
                        diagRightConnect.updateBoardConnection("diagonal right", count, j + 1, i - 1);

                        if (board[i - 2][j + 2] == player) {
                            count = count + 1;
                            diagRightConnect.updateBoardConnection("diagonal right", count, j + 2, i - 2);

                            if (board[i - 3][j + 3] == player) {
                                count = count + 1;
                                diagRightConnect.updateBoardConnection("diagonal right", count, j + 3, i - 3);

                                if (count >= 4) {
                                    return diagRightConnect;
                                }
                                else{
                                    count = 0;
                                }
                            }
                            else{
                                count = 0;
                            }
                        }
                        else{
                            count = 0;
                        }
                    }
                    else{
                        count = 0;
                    }
                }
                else{
                    count = 0;
                }
            }
        }

        // check diagonal, left upwards
        count = 0;
        BoardConnection diagLeftConnect = new BoardConnection();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) { // scan across width
                if (board[i][j] == player) {
                    count = count + 1;
                    diagLeftConnect.updateBoardConnection("diagonal left", count, j, i);
                    if (board[i + 1][j + 1] == player) {
                        count = count + 1;
                        diagLeftConnect.updateBoardConnection("diagonal left", count, j , i);
                        if (board[i + 2][j + 2] == player) {
                            count = count + 1;
                            diagLeftConnect.updateBoardConnection("diagonal left", count, j , i);
                            if (board[i + 3][j + 3] == player) {
                                count = count + 1;
                                diagLeftConnect.updateBoardConnection("diagonal left", count, j, i);
                                if (count >= 4) {
                                    return diagLeftConnect;
                                }
                                else{
                                    count = 0;
                                }

                            }
                            else{
                                count = 0;
                            }
                        }
                        else{
                            count = 0;
                        }
                    }
                    else{
                        count = 0;
                    }
                }
                else{
                    count = 0;
                }

            }
        }
        // calculate longest connection
        BoardConnection longestConnection = calculateLongestConnection(horizConnect, vertConnect, diagRightConnect,
                diagLeftConnect);

        return longestConnection;
    }

    // method to calculate longest connection
    public BoardConnection calculateLongestConnection(BoardConnection hor, BoardConnection vert, BoardConnection diaR,
                                                      BoardConnection diaL) {

        // establish lengths of connections
        int hLength = hor.returnConnectionLength();
        int vLength = vert.returnConnectionLength();
        int dRLength = diaR.returnConnectionLength();
        int dLLength = diaL.returnConnectionLength();
        // set longestConnection
        BoardConnection longestConnection = hor;

        // check other lengths
        if (vLength > hLength && vLength > dRLength && vLength > dLLength) {
            longestConnection = vert;
        }
        if (dRLength > hLength && dRLength > vLength && dRLength > dLLength) {
            longestConnection = diaR;
        }
        if (dLLength > hLength && dLLength > vLength && dLLength > dRLength) {
            longestConnection = diaL;
        }

        return longestConnection;
    }


}
