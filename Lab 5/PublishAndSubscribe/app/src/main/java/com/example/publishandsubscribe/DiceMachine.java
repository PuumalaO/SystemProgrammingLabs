package com.example.publishandsubscribe;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class DiceMachine extends Thread {

    private int diceSize;
    private Context context;

    public DiceMachine(Context context, int diceSize) {
        this.diceSize = diceSize;
        this.context = context;
    }

    @Override
    public void run() {
        while(true){

        int randomNumber = new Random().nextInt(diceSize)+1;
        Log.d("debug", "new dice value: "+randomNumber);
        diceThrown(randomNumber);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        }
    }

    private void diceThrown(int result){
        Intent intent =  new Intent("diceNum");
        intent.putExtra("diceValue", result);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
