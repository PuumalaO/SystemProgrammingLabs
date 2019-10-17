package com.example.oskari.androidtamagochis;

import java.util.concurrent.ThreadLocalRandom;
public class TamagochiThread extends Thread{

    public interface TamagochiReporterInterface{
        void eat(int tamagochiId, int foodAmount);
        void died(int tamagochiId);
        void interrupted();
    }

    private static int tamagochiCount = -1;
    private static int tamagochisAwake = 0;
    private int tamagochiId;
    private int foodAmount;
    private int maxFood;
    private boolean runGame;
    private TamagochiReporterInterface callbackInterface;

    public TamagochiThread(int startFood, int maxFood, TamagochiReporterInterface callback){
        callbackInterface = callback;
        this.foodAmount = startFood;
        this.maxFood = maxFood;
        tamagochiCount++;
        tamagochiId = tamagochiCount;
        runGame = true;
    }

    public void run(){

        try {
            int randomTime = ThreadLocalRandom.current().nextInt(1, 3000);
            java.lang.Thread.sleep(randomTime);
            tamagochisAwake++;

        } catch (Exception e) {
            e.printStackTrace();
        }
        while(runGame) {
        try {
            while (foodAmount >= 0 && foodAmount <= maxFood) {
                int eatTime = ThreadLocalRandom.current().nextInt(500, 3000);
                callbackInterface.eat(tamagochiId, foodAmount);
                foodAmount--;
                sleep(eatTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackInterface.interrupted();
        }
        callbackInterface.died(tamagochiId);
        }
    }

    public void feed(int feedingAmount){
        foodAmount+=feedingAmount;
    }

    public void killThread(){
        runGame = false;
    }

    public int getTamagochiId(){
        return this.tamagochiId;
    }

    public void resetThreads(){
        tamagochiCount = -1;
        tamagochisAwake = 0;
    }

}