package com.example.op.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyCalculator2 extends AppCompatActivity {

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonAdd;
    Button buttonSub;
    Button buttonMul;
    Button buttonDiv;
    Button buttonClear;
    Button buttonResult;

    String result;
    String tmp;
    String operator;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calculator2);

        initControl();
        initControlListener();
    }

    private void onNumberClickButton(String s) {
        result = textView.getText().toString();

        result = result + s;
        textView.setText(result);
    }

    private void initControlListener() {
        button0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onNumberClickButton("9");
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onClearClickButton();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperateClickButton("+");
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperateClickButton("-");
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperateClickButton("*");
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperateClickButton("/");
            }
        });

        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResultClickButton();
            }
        });
    }

    private void onResultClickButton() {
        Integer result = 0;

        try{
            Integer a = Integer.parseInt(tmp);
            Integer b = Integer.parseInt(textView.getText().toString());

            switch (operator){
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b != 0)
                        result = a / b;
                    else result = 0;
                    break;

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        textView.setText(result.toString());
    }

    private void onOperateClickButton(String s) {
        tmp = textView.getText().toString();
        onClearClickButton();
        operator = s;
    }

    private void onClearClickButton() {
        result = "";
        operator = "";
        textView.setText("");
    }


    private void initControl() {
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonDiv = (Button) findViewById(R.id.buttonDiv);

        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonResult = (Button) findViewById(R.id.buttonResult);

        textView = (TextView) findViewById(R.id.textViewResult);

    }
}
