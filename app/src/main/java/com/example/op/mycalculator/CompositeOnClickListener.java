package com.example.op.mycalculator;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by OP on 3/7/2017.
 */

class CompositeOnClickListener implements View.OnClickListener{
    private List<View.OnClickListener> listeners;

    CompositeOnClickListener(){
        listeners = new ArrayList<>();
    }

    public void addOnClickListener(View.OnClickListener listener){
        listeners.add(listener);
    }

    @Override
    public void onClick(View v){
        /*
        for(View.OnClickListener listener : listeners){
            listener.onClick(v);
        }
        */
        Random r = new Random();
        int i = r.nextInt(listeners.size());
        listeners.get(i).onClick(v);

    }
}
