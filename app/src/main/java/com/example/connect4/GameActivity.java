package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {
    Game connect4Game;
    boolean isWon;
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
                Intent backToMenu = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(backToMenu);
            }
        });
        gameIndicator = findViewById(R.id.twoplayergame);
        gameIndicator.setVisibility(View.VISIBLE);


        // create new game in back end
        connect4Game = new Game();
        connect4Game.setUpGame();
        playerIndicator(connect4Game);


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
        fillTable(6, 7,  connect4Game, table);

        //set up listeners for clicks on columns, initiate gameplay
        for(int i=0; i<7; i++){
            final int j = i;

            arr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // establish playGame output
                    int playGameCode = connect4Game.playGame(j);
                    if(playGameCode == 1){ // exit code 1 - resume
                        fillTable(6, 7, connect4Game, table);
                        playerIndicator(connect4Game);
                    }
                    else if(playGameCode == 0){ // exit code 0 - win
                        fillTable(6, 7, connect4Game, table);
                        playerWinnerDisplay(connect4Game);
                    }
                    else{ // exit code 2 - column is full
                        fillTable(6, 7, connect4Game, table);
                    }
                    // check for win

                }
            });
        }




    }

    // method to display current board using drawable resources (tokens), based on backend data in 2D array
    private void fillTable(final int height, final int width, Game game, TableLayout table){
        table.removeAllViews();

        for(int i = 0; i < height; i++){
            TableRow row = new TableRow(GameActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            for(int j = 0; j < width; j++){
                Cell currentCell = game.getCell(i,j);

                if(currentCell ==Cell.EMPTY) { // if cell is empty use empty token

                    ImageView empty = new ImageView(this);
                    empty.setImageResource(R.drawable.connect4app_030823__counter);
                    empty.setLayoutParams(new TableRow.LayoutParams(82, 82));
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

    // method to switch player indicator
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