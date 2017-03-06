package com.example.op.mycalculator;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Album extends AppCompatActivity {
    Spinner spinnerNumImages;
    final int[] drawableIds = {
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
    };
    GridLayout gridAlbum;

    private void setViewByOrientation() {
        int orientation = getScreenOrientation();

        if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            changeUILandscape();
        } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        {
            changeUIPortrait();
        }

        doAnimation();
    }

    private void changeUIPortrait() {
        if (gridAlbum == null)
            gridAlbum = (GridLayout) findViewById(R.id.gridAlbum);

        String numViews = String.valueOf(gridAlbum.getChildCount());
        gridAlbum.removeAllViewsInLayout();
        gridAlbum.setColumnCount(4);
        loadImages(numViews);
    }

    private void changeUILandscape() {
        if (gridAlbum == null)
            gridAlbum = (GridLayout) findViewById(R.id.gridAlbum);

        gridAlbum.setColumnCount(7);
    }

    private void doAnimation() {
        int nView = gridAlbum.getChildCount();

        for (int i = 0; i < nView; i++)
        {
            ImageView image = (ImageView) gridAlbum.getChildAt(i);

            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shaking);

            image.startAnimation(anim);
        }
    }

    private int getScreenOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        return ScreenHelper.getInstance().getScreenOrientation(dm, rotation);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        setViewByOrientation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        spinnerNumImages = (Spinner) findViewById(R.id.spinnerNumImages);
        gridAlbum = (GridLayout)findViewById(R.id.gridAlbum);

        setViewByOrientation();

        List<String> spinnerArray = new ArrayList<>();

        //Fill data in spinner
        for (int i = 0; i < 10; i++)
            spinnerArray.add(String.valueOf(i + 1));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumImages.setAdapter(adapter);

        //Load images
        spinnerNumImages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(Album.this, item.toString(),
                            Toast.LENGTH_SHORT).show();

                    loadImages(item.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void loadImages(String nRequire) {
        int nResources = drawableIds.length;
        int n;

        try {
            n = Integer.parseInt(nRequire);
        }catch (Exception e)
        {
            n= 0;
        }

        if (n  > nResources)
            n = nResources;

        gridAlbum.removeAllViewsInLayout();
        for (int i = 0; i < n; i++)
        {

            ImageView imageView =  new ImageView(Album.this);

            GridView.LayoutParams params = new GridView.LayoutParams(250,250);

            imageView.setLayoutParams(params);
            imageView.setPadding(10,10,10,10);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Bitmap bmp = BitMapHelper.decodeSampledBitmapFromResource(getResources(), drawableIds[i], 100,100);
            imageView.setImageBitmap(bmp);

            gridAlbum.addView(imageView);

        }
    }
}
