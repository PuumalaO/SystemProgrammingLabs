package com.example.oskari.androidtamagochis;

public class Tamagochi{

    private int tamagochiId;
    private int food = 0;
    private boolean alive;

    public Tamagochi(int food, int tamagochiId){
        this.tamagochiId = tamagochiId;
        this.food = food;
        alive = true;
    }

    public void kill(){
        alive = false;
    }             

    public void setFood(int food){
        this.food = food;
    }

    public int getTamagochiId(){
        return tamagochiId;
    }

    public int getFoodAmount(){
        return food;
    }

    public boolean isAlive(){
        return alive;
    }
}