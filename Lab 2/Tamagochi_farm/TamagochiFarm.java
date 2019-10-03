import java.util.Scanner;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;

class TamagochiFarm implements TamagochiThread.TamagochiReporterInterface, PrinterThread.PrinterInterface{
   
    public final static int startFood = 10;
    public final static int maxFood = 20;
    public final static int feedingAmount = 10;
    public final int tamagochis = 7;
    private boolean gameOver = false;
    private ArrayList<TamagochiThread> tamagochiThreads = new ArrayList<TamagochiThread>();
    private ArrayList<Tamagochi> tamagochiList = new ArrayList<Tamagochi>();
    private PrinterThread printer = null;
    final String manyNewlines = "\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------";
    private TamagochiThread tamagochiThread = null;
    public static void main(String args[]){
        TamagochiFarm game = new TamagochiFarm();
        game.wakeTamagochis(startFood, maxFood, game);
        game.gameLoop();

    }

    public void wakeTamagochis(int startFood, int maxFood, TamagochiFarm callback){
        printer = new PrinterThread(callback);
        System.out.println(tamagochis+" Tamagochis wake up and are hungry. Feed them as quickly as you can!");
        for(int i = 0; i <tamagochis; i++){
        tamagochiThread = new TamagochiThread(startFood, maxFood, callback);
            
            tamagochiThreads.add(tamagochiThread);
            Tamagochi tamagochi = new Tamagochi(startFood, tamagochiThread.getTamagochiId());
            tamagochiList.add(tamagochi);
        }
    }

    public void gameLoop(){
        printer.start();
        String command;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("starting game loop");
        for(int i = 0; i < tamagochiThreads.size(); i++){
            tamagochiThreads.get(i).start();
        }
        
        while(!gameOver){
            try{
                command = reader.readLine();
                int i = Integer.valueOf(command);
                if(i >= 0 || i < tamagochis){
                    tamagochiThreads.get(i).feed(feedingAmount);
                    System.out.println("feeding tamagochi no. "+i);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
            
        }
    }

    public void eat(int tamagochiId, int foodAmount){
        tamagochiList.get(tamagochiId).setFood(foodAmount);

    }

    public void died(int tamagochiId){
        tamagochiList.get(tamagochiId).kill();
    }

    public void interrupted(){

    }

    public void printTamagochis(){
        //System.out.println(manyNewlines);
        System.out.print(String.format("\033[%d;%dH", 0, 0));
        System.out.print("'\033[2J");
        
        String status;
        for(int i = 0; i < tamagochiThread.tamagochisAwake; i++){
            Tamagochi currentTamagochi = tamagochiList.get(i);
            if(currentTamagochi.isAlive()) { status = "alive"; }
            else{ status = "dead"; }
            System.out.println("Tamagochi no. "+currentTamagochi.getTamagochiId()+" is "+status+" and has "+currentTamagochi.getFoodAmount()+" food left.");
        }
    }
}