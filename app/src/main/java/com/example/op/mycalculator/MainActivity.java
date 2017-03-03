package com.example.op.mycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonCalculator(View view) {
        Intent intent = new Intent(MainActivity.this, MyCalculator.class);
        startActivity(intent);
    }

    public void onClickButtonMineSweeper(View view) {
        Intent intent = new Intent(MainActivity.this, MineSweeper.class);
        startActivity(intent);
    }

    public void onClickButtonCalculator2(View view) {
        Intent intent = new Intent(MainActivity.this, MyCalculator2.class);
        startActivity(intent);
    }

    public void onClickButtonRotate(View view) {
        Intent intent = new Intent(MainActivity.this, RotateScreen.class);
        startActivity(intent);
    }

    public void onClickButtonGuessGame(View view) {
        Intent intent = new Intent(MainActivity.this, GuessGame.class);
        startActivity(intent);
    }

    public void onClickButtonMatchGame(View view) {
        Intent intent = new Intent(MainActivity.this, MatchGame.class);
        startActivity(intent);
    }

    public void onClickButtonAlbum(View view) {
        Intent intent = new Intent(MainActivity.this, Album.class);
        startActivity(intent);
    }
}
