package com.example.oskari.asynctaskloader;

import java.util.Observable;
import java.lang.Thread;
import java.util.ArrayList;

public class Loading extends Observable implements Runnable {
    private int loading;
    @Override
    public void run() {
        loading = 0;
        while(loading < 100){
            loading = loading + 10;
            setChanged();
            notifyObservers(loading);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}