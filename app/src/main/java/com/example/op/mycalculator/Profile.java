package com.example.op.mycalculator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.alexzh.circleimageview.CircleImageView;
import com.alexzh.circleimageview.ItemSelectedListener;

public class Profile extends AppCompatActivity implements ItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int id = i.getExtras().getInt("id");

        CircleImageView circleImageView = (CircleImageView ) findViewById(R.id.user_profile_photo);


        Bitmap bmp = BitMapHelper.decodeSampledBitmapFromResource(this.getResources(),id, 150,150);
        circleImageView.setImageBitmap(bmp);

        //circleImageView.setImageResource(id);
        circleImageView.setOnItemSelectedClickListener(this);
    }

    @Override
    public void onSelected(View view) {
    }

    @Override
    public void onUnselected(View view) {
    }
}
