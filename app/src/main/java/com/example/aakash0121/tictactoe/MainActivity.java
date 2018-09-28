package com.example.aakash0121.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] blockState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; //2 for unfilled block, 0 for player1, 1 for player2
    int[][] winningLocations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameOver = false;
    int moves = 0;
    public void gameLogic(View view){

        int tappedLocation = Integer.parseInt(view.getTag().toString());

        if(blockState[tappedLocation] == 2 && !gameOver) {

            ImageView tappedView = (ImageView) view;
            tappedView.setTranslationY(-3000f);
            if (activePlayer == 0) {

                tappedView.setImageResource(R.drawable.red_dot);
                blockState[tappedLocation] = activePlayer;
                moves += 1;
                activePlayer = 1;

            } else {

                tappedView.setImageResource(R.drawable.x);
                blockState[tappedLocation] = activePlayer;
                moves += 1;
                activePlayer = 0;

            }


            tappedView.animate().translationYBy(3000f).setDuration(100);
        }

        for(int[] winningPostion : winningLocations){

            if(blockState[winningPostion[0]] == blockState[winningPostion[1]] &&
                    blockState[winningPostion[1]] == blockState[winningPostion[2]] &&
                    blockState[winningPostion[0]] == blockState[winningPostion[2]] &&
                    blockState[winningPostion[0]] != 2){

                if(blockState[winningPostion[0]] == 0){

                    Toast.makeText(getApplicationContext(), "Player1 wins", Toast.LENGTH_SHORT).show();
                    gameOver = true;

                }else if(blockState[winningPostion[0]] == 1){

                    Toast.makeText(getApplicationContext(), "Player2 wins", Toast.LENGTH_SHORT).show();
                    gameOver = true;

                }
            }
        }

        if (moves == blockState.length && !gameOver){
            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
