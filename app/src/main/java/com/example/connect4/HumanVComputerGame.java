package com.example.connect4;
import java.util.ArrayList;
import java.util.Random;


public class HumanVComputerGame extends Game{

    //fields
    protected ComputerPlayer player2;


    // constructors
    public HumanVComputerGame() {
        super();
        player2 = new ComputerPlayer(Cell.PLAYER_2, "2");
    }

    // methods

    public void player1Move(int playerInput) {
        // add counter to board from GameActivity onClickListeners
        board.addCounter(Cell.PLAYER_1, playerInput);
    }
    public void player2MoveAI() { // harder computer player
        // add counter to board from GameActivity onClickListeners
        BoardConnection player1Connect = board.checkBoard(Cell.PLAYER_1);
        board.addCounter(Cell.PLAYER_2, player2.computerInputAI(player1Connect, this.board));
    }
    public void player2Move(){ // easy computer player
        // find empty columns
        ArrayList<Integer> emptyCols = board.returnEmptyColumns();


        // generate random index
        Random rand = new Random();
        int index = rand.nextInt(emptyCols.size());
        int computerMove = emptyCols.get(index);
        board.addCounter(Cell.PLAYER_2, computerMove);

    }

    public boolean isColumnFull(int column){
        return board.isColumnFull(column);
    }

    public boolean checkConnections(){
        // check board for connections
        BoardConnection connectPlayer1 = board.checkBoard(Cell.PLAYER_1);
        BoardConnection connectPlayer2 = board.checkBoard(Cell.PLAYER_2);

        if(connectPlayer1.returnConnectionLength() >= 4 || connectPlayer2.returnConnectionLength() >= 4) {
            win = true;
        }
        return win;

    }

}