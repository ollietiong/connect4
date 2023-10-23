package com.example.connect4;

public class Game {
    //fields

    protected Board board;
    protected boolean win;
    protected HumanPlayer player1;
    protected HumanPlayer player2;
    protected Player currentPlayer;

    //constructors
    public Game(){
        board = new Board();
        this.win = false;
        player1 = new HumanPlayer(Cell.PLAYER_1, "1");
        player2 = new HumanPlayer(Cell.PLAYER_2, "2");
        this.currentPlayer = player1;
    }

    //methods

    public boolean getGameStatus(){
        return win;
    }

    public void setUpGame(){
        board.setUpBoard();
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Cell getCell(int i, int j){
        return board.getBoardCell(i,j);
    }


    public int playGame(int playerInput){
        int output = 1;
        if(board.isColumnFull(playerInput)==true){
            output = 2;
        }
        else{
            playerMove(currentPlayer, playerInput);

        }
        // check for win

        BoardConnection connect = board.checkBoard(currentPlayer.getPlayerCell());

        //unit test
        int length = connect.returnConnectionLength();
        String type = connect.returnConnectionType();
        System.out.println(length);
        System.out.println(type);

        if(connect.returnConnectionLength() >= 4) {
            output = 0;
        }
        else{
            switchPlayer();
        }



        //check if column full
        //if(board.isColumnFull(playerInput)==false){
            // check for any winning connections, if none playerMove
            /*if(checkConnections(player1)==false && checkConnections(player2)==false){
                playerMove(currentPlayer, playerInput);
                if(checkConnections(currentPlayer)==true){
                    output = 0; // win for current player
                }
                else{
                    switchPlayer();
                    output = 1;
                }
            }
        //}

        else{
            output = 2; // column is full
        }*/

        return output;
    }


    public void switchPlayer(){
        if (currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }

    public void playerMove(Player currentPlayer, int playerInput) {
        // add counter to board from GameActivity onClickListeners
        board.addCounter(currentPlayer.getPlayerCell(), playerInput);
    }
    public boolean checkConnections(Player currentPlayer){
        // check board for connections
        BoardConnection connect = board.checkBoard(currentPlayer.getPlayerCell());
        if(connect.returnConnectionLength() >= 4) {
            win = true;
        }
        return win;

    }


}
