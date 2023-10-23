package com.example.connect4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView welcomeText;
    TextView letterCOne;
    TextView letterO;
    TextView letterNminusOne;
    TextView letterNZero;
    TextView letterE;
    TextView letterCTwo;
    TextView letterT;
    TextView letterFour;
    TextView connect4;
    Button startbutton;
    Button startCompButton;
    TextView[] connect4Arr;
    AnimatorSet[] connect4AnimSet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up textviews
        welcomeText = findViewById(R.id.welcometext);

        connect4Arr = new TextView[8];
        connect4Arr[0] = findViewById(R.id.c1);
        connect4Arr[1] = findViewById(R.id.o);
        connect4Arr[2] = findViewById(R.id.n_minus1);
        connect4Arr[3] = findViewById(R.id.n_0);
        connect4Arr[4] = findViewById(R.id.e);
        connect4Arr[5] = findViewById(R.id.c2);
        connect4Arr[6] = findViewById(R.id.t);
        connect4Arr[7] = findViewById(R.id.four);

        connect4 = findViewById(R.id.connect4);
        connect4.setVisibility(View.INVISIBLE);
        startbutton = findViewById(R.id.startbutton);
        startCompButton = findViewById(R.id.startcompbutton);

        // create animation from textviews
        AnimatorSet anim = new AnimatorSet();

        //create welcome animation
        ObjectAnimator welcome = ObjectAnimator.ofFloat(welcomeText, "translationY", -1300, 0);
        welcome.setDuration(4000);
        welcome.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {}

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                welcomeText.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {}
        });

        // create letter drop sets with bounce
        connect4AnimSet = new AnimatorSet[8];

        for(int i = 0; i < 8; i++){
            connect4AnimSet[i] = letterAnim(connect4Arr[i]);
        }

        // set order of letter drops
        anim.play(welcome).before(connect4AnimSet[2]);
        anim.play(connect4AnimSet[6]).after(connect4AnimSet[2]);
        anim.play(connect4AnimSet[4]).after(connect4AnimSet[6]);
        anim.play(connect4AnimSet[1]).after(connect4AnimSet[4]);
        anim.play(connect4AnimSet[5]).after(connect4AnimSet[1]);
        anim.play(connect4AnimSet[0]).after(connect4AnimSet[5]);
        anim.play(connect4AnimSet[3]).after(connect4AnimSet[0]);
        anim.play(connect4AnimSet[7]).after(connect4AnimSet[3]);

        //start animation of letters
        anim.start();

        // listen for end of animation to fade in menu buttons
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                //
                for(int i=0; i<8; i++){
                    connect4Arr[i].setVisibility(View.INVISIBLE);
                }
                connect4.setVisibility(View.VISIBLE);
                startbutton.setVisibility(View.VISIBLE);
                startCompButton.setVisibility(View.VISIBLE);

                ObjectAnimator moveconnect4 = ObjectAnimator.ofFloat(connect4, "translationY", 0, -500);
                moveconnect4.setDuration(1000);
                moveconnect4.start();
                ObjectAnimator fadein = ObjectAnimator.ofFloat(startbutton, "alpha", 0, 1f);
                fadein.setDuration(1000);
                fadein.start();
                ObjectAnimator fadein2 = ObjectAnimator.ofFloat(startCompButton, "alpha", 0, 1f);
                fadein2.setDuration(1000);
                fadein2.start();
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {}
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gamePlay = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gamePlay);
            }
        });
        startCompButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gamePlay = new Intent(MainActivity.this, ComputerGameActivity.class);
                startActivity(gamePlay);
            }
        });

    }



    private AnimatorSet letterAnim( TextView text){
        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator letter = ObjectAnimator.ofFloat(text, "translationY", 0, 800);
        letter.setDuration(500);
        ObjectAnimator bounce1 = ObjectAnimator.ofFloat(text, "translationY", 800,750,800);
        bounce1.setDuration(300);
        ObjectAnimator bounce2 = ObjectAnimator.ofFloat(text, "translationY", 800,775,800);
        bounce2.setDuration(150);
        ObjectAnimator bounce3 = ObjectAnimator.ofFloat(text, "translationY", 800,788,800);
        bounce2.setDuration(75);

        animSet.play(letter).before(bounce1);
        animSet.play(bounce1).before(bounce2);
        animSet.play(bounce3).after(bounce2);

        return animSet;
    }




}