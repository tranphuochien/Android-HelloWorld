package com.example.op.mycalculator;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Integer> listIds = new ArrayList<Integer>();

    public ImageAdapter(Context context, ArrayList<Integer> list) {
        int nImages = list.size();
        for (int i = 0; i < nImages; i++)
            this.listIds.add(list.get(i));
        this.context = context;
    }

    @Override
    public int getCount() {
        return listIds.size();
    }

    @Override
    public Object getItem(int i) {
        return this.getView(i, null, null );
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if (view == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(250,250));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(10,10,10,10);
        } else {
            imageView = (ImageView)view;
        }

        Bitmap bmp = BitMapHelper.decodeSampledBitmapFromResource(context.getResources(), listIds.get(i), 50,50);
        imageView.setImageBitmap(bmp);

        //Just for fun: animation
        //Animation anim = AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.shaking);
        //imageView.startAnimation(anim);
        //imageView.setImageResource(listIds.get(i));

        return imageView;
    }
}
