package com.example.op.mycalculator;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MatchGame extends AppCompatActivity {
    ImageView currView = null;
    int currPosition = -1;
    final int[] drawable = {
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
    };
    int[] pos = {0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7};
    private int cntPair = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_game);

        GridView gridMatching = (GridView)findViewById(R.id.gridMatching);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 16; i++)
            list.add(R.drawable.hidden);
        ImageAdapter imageAdapter = new ImageAdapter(this, list );

        gridMatching.setAdapter(imageAdapter);

        gridMatching.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int position, long l) {
                if (currPosition < 0) {
                    currPosition = position;
                    currView = (ImageView) view;
                    ((ImageView) view).setImageResource(drawable[pos[position]]);
                }
                else  {
                    if (currPosition == position) {
                        ((ImageView) view).setImageResource(R.drawable.hidden);
                    }
                    else if (pos[currPosition] != pos[position]) {
                        ((ImageView) view).setImageResource(drawable[pos[position]]);

                        Handler handler=new Handler();
                        Runnable r=new Runnable() {
                            public void run() {
                                currView.setImageResource(R.drawable.hidden);
                                ((ImageView)view).setImageResource(R.drawable.hidden);
                            }
                        };
                        handler.postDelayed(r, 2000);

                        Toast.makeText(MatchGame.this, "Not match...", Toast.LENGTH_SHORT).show();
                    }
                    else if (pos[currPosition] == pos[position]){
                        ((ImageView) view).setImageResource(drawable[pos[position]]);

                        cntPair++;
                        if (cntPair == 8)
                            Toast.makeText(MatchGame.this, "You win!", Toast.LENGTH_SHORT).show();
                }

                    currPosition = -1;
                }
            }
        });

    }
}
