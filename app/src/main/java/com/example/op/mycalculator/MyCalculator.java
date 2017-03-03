package com.example.op.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calculator);

        ((EditText) findViewById(R.id.result)).setEnabled(false);
        ((Button)findViewById(R.id.buttonAdd)).setOnClickListener(onClickListener);
        ((Button)findViewById(R.id.buttonSub)).setOnClickListener(onClickListener);
        ((Button)findViewById(R.id.buttonMul)).setOnClickListener(onClickListener);
        ((Button)findViewById(R.id.buttonDiv)).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            String s1 = ((EditText) findViewById(R.id.operand1)).getText().toString();
            String s2 = ((EditText) findViewById(R.id.operand2)).getText().toString();

            Integer num1, num2, result = 0;
            num1 = Integer.parseInt(s1);
            num2 = Integer.parseInt(s2);

            if (view == findViewById(R.id.buttonAdd))
                result = num1 + num2;
            else if (view == findViewById(R.id.buttonSub))
                result = num1 - num2;
            else if (view == findViewById(R.id.buttonMul))
                result = num1 * num2;
            else if (view == findViewById(R.id.buttonDiv))
            {
                if (num2 != 0)
                    result = num1 / num2;
                else result = 0;
            }


            ((EditText) findViewById(R.id.result)).setText(result.toString());
        }
    };
}
