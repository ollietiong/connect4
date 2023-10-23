package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;

public class ComputerGameActivity extends AppCompatActivity {

    HumanVComputerGame connect4CompGame;
    boolean isWon;
    boolean test;
    ArrayList<Integer> colsEmpty;
    Button backButton;
    TextView gameIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        // set back button and indicator functionality
        backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToMenu = new Intent(ComputerGameActivity.this, MenuActivity.class);
                startActivity(backToMenu);
            }
        });
        gameIndicator = findViewById(R.id.oneplayergame);
        gameIndicator.setVisibility(View.VISIBLE);



        // create new game in back end
        connect4CompGame = new HumanVComputerGame();
        connect4CompGame.setUpGame();
        playerIndicator(connect4CompGame);

        // link table layout from resources
        TableLayout table = findViewById(R.id.tableLayout1);

        // set up invisible buttons behind columns into array
        Button[] arr;
        arr= new Button[7];

        arr[0] = findViewById(R.id.column1Button);
        arr[1] = findViewById(R.id.column2Button);
        arr[2] = findViewById(R.id.column3Button);
        arr[3] = findViewById(R.id.column4Button);
        arr[4] = findViewById(R.id.column5Button);
        arr[5] = findViewById(R.id.column6Button);
        arr[6] = findViewById(R.id.column7Button);

        // show blank board
        fillTable(6, 7,  connect4CompGame, table);

        //main gameplay
        for(int i=0; i<7; i++){
            final int j = i;

            // set up listeners
            arr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //check for winning connections
                    isWon = connect4CompGame.checkConnections();

                    //check for empty columns i.e. board is not full
                    colsEmpty = connect4CompGame.board.returnEmptyColumns();

                    if(colsEmpty.size() > 0) {
                        //player1 move
                        if (!isWon) {
                            if (connect4CompGame.board.isColumnFull(j) == false) {
                                connect4CompGame.player1Move(j);
                                fillTable(6, 7, connect4CompGame, table);
                                isWon = connect4CompGame.checkConnections();
                                if(isWon == true){
                                    playerWinnerDisplay(connect4CompGame);
                                }
                                else {
                                    connect4CompGame.switchPlayer();
                                    playerIndicator(connect4CompGame);
                                }
                            }
                        }
                        //check for winning connections
                        isWon = connect4CompGame.checkConnections();
                        // player 2 (computer move)
                        if (!isWon) {
                            final Handler handler = new Handler(); // add delay before computer adds its counter
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    connect4CompGame.player2MoveAI();
                                    fillTable(6, 7, connect4CompGame, table);
                                    isWon = connect4CompGame.checkConnections();
                                    if(isWon == true){
                                        playerWinnerDisplay(connect4CompGame);
                                    }
                                    else {
                                        connect4CompGame.switchPlayer();
                                        playerIndicator(connect4CompGame);
                                    }
                                }
                            }, 1000);
                        }
                    }
                    // ADD ELSE CLAUSE TO ADD GRAPHIC THAT GAME IS A DRAW
                }
            });
        }
    }

    // method to display current board using drawable resources (tokens), based on backend data in 2D array
    private void fillTable(final int height, final int width, HumanVComputerGame game, TableLayout table){
        table.removeAllViews();

        for(int i = 0; i < height; i++){
            TableRow row = new TableRow(ComputerGameActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            for(int j = 0; j < width; j++){
                Cell currentCell = game.getCell(i,j);

                if(currentCell ==Cell.EMPTY) { // if cell is empty use empty token

                    ImageView empty = new ImageView(this);
                    empty.setImageResource(R.drawable.connect4app_030823__counter);
                    empty.setLayoutParams(new TableRow.LayoutParams(82,82));
                    row.addView(empty);
                }
                else if(currentCell ==Cell.PLAYER_1) { // if cell is player1 use red token

                    ImageView player1 = new ImageView(this);
                    player1.setImageResource(R.drawable.connect4app_030823_redtoken);
                    player1.setLayoutParams(new TableRow.LayoutParams(82, 82));
                    row.addView(player1);
                }
                else {

                    ImageView player2 = new ImageView(this);
                    player2.setImageResource(R.drawable.connect4app_030823_yellowtoken);
                    player2.setLayoutParams(new TableRow.LayoutParams(82, 82));
                    row.addView(player2);
                }

            }
            table.addView(row);
        }
    }
    private void playerIndicator(Game game){
        TextView player1Ind = findViewById(R.id.player1);
        TextView player2Ind = findViewById(R.id.player2);


        Player current = game.getCurrentPlayer();
        Cell currentCell = current.getPlayerCell();

        if(currentCell == Cell.PLAYER_1){
            player1Ind.setAlpha(1.0f);
            player2Ind.setAlpha(0.5f);
        }
        else{
            player2Ind.setAlpha(1.0f);
            player1Ind.setAlpha(0.5f);
        }


    }

    private void playerWinnerDisplay(Game game){
        TextView player1win = findViewById(R.id.player1wins);
        TextView player2win = findViewById(R.id.player2wins);
        Player current = game.getCurrentPlayer();
        Cell currentCell = current.getPlayerCell();

        if(currentCell == Cell.PLAYER_1){
            player1win.setVisibility(View.VISIBLE);
        }
        else{
            player2win.setVisibility(View.VISIBLE);
        }

    }
}

