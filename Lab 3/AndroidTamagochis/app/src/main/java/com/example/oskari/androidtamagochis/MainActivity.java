package com.example.oskari.androidtamagochis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TamagochiThread.TamagochiReporterInterface, PrinterThread.PrinterInterface {

    public final static int startFood = 10;
    public final static int maxFood = 20;
    public final static int feedingAmount = 10;
    public final int tamagochis = 5;
    private int deadTamagochis;
    private boolean gameOver = false;
    private ArrayList<TamagochiThread> tamagochiThreads;
    private ArrayList<Tamagochi> tamagochiList;
    private PrinterThread printer = null;
    private TamagochiThread tamagochiThread = null;

    private ArrayList<TamagochiView> tamagochiViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tamagochiViews = new ArrayList<TamagochiView>();
        TamagochiView tamagochiView1 = findViewById(R.id.tamagochi1);
        TamagochiView tamagochiView2 = findViewById(R.id.tamagochi2);
        TamagochiView tamagochiView3 = findViewById(R.id.tamagochi3);
        TamagochiView tamagochiView4 = findViewById(R.id.tamagochi4);
        TamagochiView tamagochiView5 = findViewById(R.id.tamagochi5);
        tamagochiViews.add(tamagochiView1);
        tamagochiViews.add(tamagochiView2);
        tamagochiViews.add(tamagochiView3);
        tamagochiViews.add(tamagochiView4);
        tamagochiViews.add(tamagochiView5);
        handleGameStart();

        for(int i = 0; i < tamagochiViews.size(); i++){
            final int a = i;
            tamagochiViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tamagochiThreads.get(a).feed(feedingAmount);
                }
            });
        }
    }

    public void handleGameStart(){
        deadTamagochis = 0;
        wakeTamagochis(startFood, maxFood, this);
        startGame();
    }

    public void showGameOver(){
        new AlertDialog.Builder(this)
                .setTitle("Game over")
                .setMessage("All tamagochis died")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        handleGameStart();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void wakeTamagochis(int startFood, int maxFood, MainActivity callback){
        printer = new PrinterThread(callback);
        System.out.println(tamagochis+" Tamagochis wake up and are hungry. Feed them as quickly as you can!");
        tamagochiThreads = new ArrayList<TamagochiThread>();
        tamagochiList = new ArrayList<Tamagochi>();
        for(int i = 0; i <tamagochis; i++){
            tamagochiThread = new TamagochiThread(startFood, maxFood, callback);

            tamagochiThreads.add(tamagochiThread);
            Tamagochi tamagochi = new Tamagochi(startFood, tamagochiThread.getTamagochiId());
            tamagochiList.add(tamagochi);
        }
    }

    public void startGame(){
        printer.start();
        System.out.println("starting game loop");
        for(int i = 0; i < tamagochiThreads.size(); i++){
            tamagochiThreads.get(i).start();
        }
    }

    public void eat(int tamagochiId, int foodAmount){
        tamagochiList.get(tamagochiId).setFood(foodAmount);
        printTamagochis();
        Log.d("debug", "eat: Feedin tamagochi "+tamagochiId);

    }

    public void died(int tamagochiId){
        deadTamagochis++;
        tamagochiThreads.get(tamagochiId).killThread();
        tamagochiList.get(tamagochiId).kill();
        tamagochiViews.get(tamagochiId).setBackgroundColor(Color.RED);
        if(deadTamagochis == tamagochis){
            tamagochiThreads.get(tamagochiId).resetThreads();
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    showGameOver();
                }
            });
        }
    }

    @Override
    public void interrupted() {

    }

    public void setBackgroundOnUI(TamagochiView tamagochiViewArg, int colorArg){
        final int color = colorArg;
        final TamagochiView tamagochiView = tamagochiViewArg;
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                tamagochiView.setBackgroundColor(color);
            }
        });
    }

    public void setViewFoodOnUI(TamagochiView tamagochiViewArg, int foodLeftArg){
        final TamagochiView tamagochiView = tamagochiViewArg;
        final int foodLeft = foodLeftArg;

        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                tamagochiView.setFood(String.valueOf(foodLeft));
            }
        });
    }

    public void printTamagochis(){

        for(int i = 0; i < tamagochiViews.size(); i++){

            TamagochiView tamagochiView = tamagochiViews.get(i);
            int foodLeft = tamagochiList.get(i).getFoodAmount();
            setViewFoodOnUI(tamagochiView, foodLeft);

            if(!tamagochiList.get(i).isAlive()){
                setBackgroundOnUI(tamagochiView,Color.RED);
            }

            else if(foodLeft >=10){
                setBackgroundOnUI(tamagochiView,Color.GREEN);
            }

            else if(foodLeft >= 5 && foodLeft< 10){
                setBackgroundOnUI(tamagochiView,Color.YELLOW);
            }

            else if(foodLeft < 5){

                setBackgroundOnUI(tamagochiView,Color.RED);
            }
        }


        }
    }
