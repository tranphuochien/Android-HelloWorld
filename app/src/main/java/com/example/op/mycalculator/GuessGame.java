package com.example.op.mycalculator;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GuessGame extends AppCompatActivity {

    private int[] nImgIds = new int[] {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4};
    private String[] nAns = new String[] {"tam that", "dong cam", "than khoc", "tranh thu"};
    private int curQuestionId = 0;
    private int curScore;

    Button btnAnswer;
    CheckBox checkbox;
    EditText editTextBetScore, editTextAnswer;
    TextView textViewScore;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);

        initComponent();
        loadNextQuestion(curQuestionId);
    }

    private void loadNextQuestion(int id) {
        //Bitmap bmp = BitmapFactory.decodeResource(getResources(), nImgIds[id]);
        Bitmap bmp = BitMapHelper.decodeSampledBitmapFromResource(getResources(), nImgIds[id], 128,128);
        image.setImageBitmap(bmp);

        checkbox.setChecked(false);
        editTextBetScore.setText("");
        editTextAnswer.setText("");
    }

    private void initComponent() {
        btnAnswer = (Button)findViewById(R.id.buttonAnswer);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        editTextBetScore = (EditText) findViewById(R.id.editTextBetScore);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        image = (ImageView) findViewById(R.id.imageView);
    }

    public void onClick_btnAnswer(View view) {
        boolean betFlage = false;
        String answer = editTextAnswer.getText().toString();
        int betScore = 0;

        if (checkbox.isChecked())
        {
            betFlage = true;
            try {
                betScore = Integer.parseInt(editTextBetScore.getText().toString());

                if (betScore < 0 || betScore > curScore)
                {
                    Toast.makeText(this, "Điểm cược phải không âm và nhỏ hơn số điểm hiện có",Toast.LENGTH_LONG).show();
                    return;
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if(answer.length() == 0) {
            Toast.makeText(this, "Câu trả lời rỗng", Toast.LENGTH_LONG).show();
            return;
        }

        if (answer.toLowerCase().equals(nAns[curQuestionId]))
        {
            Toast.makeText(this, "ĐÚNG RỒI", Toast.LENGTH_LONG).show();
            curScore += 50;

            if (betFlage)
                curScore += betScore;

            if (curQuestionId < nImgIds.length - 1)
                curQuestionId++;
            else {
                Toast.makeText(this, "XONG PHIM", Toast.LENGTH_LONG).show();
                curQuestionId = 0;
            }
        } else {
            Toast.makeText(this, "SAI RỒI", Toast.LENGTH_LONG).show();

            if (betFlage)
                curScore -= betScore;
            curScore = curScore <= 0 ? 0 : curScore;
        }

        textViewScore.setText("Điểm: " + String.valueOf(curScore));
        loadNextQuestion(curQuestionId);

    }
}
