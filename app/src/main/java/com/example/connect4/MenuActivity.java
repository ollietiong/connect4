package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MenuActivity extends AppCompatActivity {

    Button startButton;
    Button startCompButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startButton = findViewById(R.id.startbutton);
        startCompButton = findViewById(R.id.startcompbutton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gamePlay = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(gamePlay);
            }
        });
        startCompButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gamePlay = new Intent(MenuActivity.this, ComputerGameActivity.class);
                startActivity(gamePlay);
            }
        });


    }
}