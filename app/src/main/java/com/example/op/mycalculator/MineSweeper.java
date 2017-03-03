package com.example.op.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.AdapterView.OnItemClickListener;

public class MineSweeper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sweeper);
    }

    //Event click button CreateGame
    public void onCreateGame(View view) {
        Integer rows =  getNumOfRows();
        Integer columns  = getNumOfColumns();

        generateCells(rows, columns);
    }

    private void generateCells(Integer rows, Integer columns) {
        Button btn;
        GridLayout gridCells = (GridLayout)findViewById(R.id.gridCells);
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++)
            {
                btn = new Button(this);
                gridCells.addView(btn);
            }
    }

    private Integer getNumOfRows() {
        String s = ((EditText) findViewById(R.id.editTextColumns)).getText().toString();
        return Integer.parseInt(s);
    }

    private Integer getNumOfColumns() {
        String s = ((EditText) findViewById(R.id.editTextRows)).getText().toString();
        return Integer.parseInt(s);
    }


}
