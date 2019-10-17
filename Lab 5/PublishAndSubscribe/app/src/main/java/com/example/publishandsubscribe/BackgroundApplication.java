package com.example.publishandsubscribe;

import android.app.Application;
import android.util.Log;

public class BackgroundApplication extends Application {

    private final int DICESIZE = 20;

    DiceMachine diceMachine = new DiceMachine(this, DICESIZE);

    @Override
    public void onCreate() {
        super.onCreate();
        diceMachine.start();
        Log.d("debug", "onCreate: starting background");
    }
}
