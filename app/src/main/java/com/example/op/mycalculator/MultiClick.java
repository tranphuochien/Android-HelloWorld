package com.example.op.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MultiClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_click);

        Button button = (Button) findViewById(R.id.buttonExucute);
        final EditText a = (EditText)findViewById(R.id.operand1);
        final EditText b = (EditText)findViewById(R.id.operand2);
        final EditText result = (EditText)findViewById(R.id.result);

        CompositeOnClickListener groupListener = new CompositeOnClickListener();
        button.setOnClickListener(groupListener);

        groupListener.addOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int x,y;
                int s;
                try {
                    x = Integer.parseInt(a.getText().toString());
                    y = Integer.parseInt(b.getText().toString());
                } catch (Exception ex){
                    x = y  =0;
                }

                s = x + y;
                result.setText(String.valueOf(s));
            }
        });

        groupListener.addOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int x,y;
                int s;
                try {
                    x = Integer.parseInt(a.getText().toString());
                    y = Integer.parseInt(b.getText().toString());
                } catch (Exception ex){
                    x = y  =0;
                }

                s = x - y;
                result.setText(String.valueOf(s));
            }
        });
    }
}
