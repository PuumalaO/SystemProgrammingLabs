import java.util.Scanner;

import java.util.ArrayList;

class TamagochiFarm implements TamagochiThread.TamagochiReporterInterface{
   
    public final static int startFood = 10;
    public final static int maxFood = 20;
    public final static int feedingAmount = 10;
    public final int tamagochis = 7;
    private boolean gameOver = false;
    private ArrayList<TamagochiThread> tamagochiThreads = new ArrayList<TamagochiThread>();
    private ArrayList<Tamagochi> tamagochiList = new ArrayList<Tamagochi>();
    final String manyNewlines = "\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------\n-----------------------------";
    public static void main(String args[]){
        TamagochiFarm game = new TamagochiFarm();
        game.wakeTamagochis(startFood, maxFood, game);
        game.gameLoop();

    }

    public void wakeTamagochis(int startFood, int maxFood, TamagochiFarm callback){
        
        System.out.println(tamagochis+" Tamagochis wake up and are hungry. Feed them as quickly as you can!");
        
        for(int i = 0; i <tamagochis; i++){
            TamagochiThread tamagochiThread = new TamagochiThread(startFood, maxFood, callback);
            tamagochiThreads.add(tamagochiThread);
            Tamagochi tamagochi = new Tamagochi(startFood, tamagochiThread.getTamagochiId());
            tamagochiList.add(tamagochi);
        }
    }

    public void gameLoop(){
        String command;
        Scanner scanner = new Scanner(System.in);
        System.out.println("starting game loop");
        for(int i = 0; i < tamagochiThreads.size(); i++){
            tamagochiThreads.get(i).start();
            try {
                java.lang.Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while(!gameOver){
            
            command = scanner.next();
            int i = scanner.nextInt();
            if(command.equals("feed")){
                tamagochiThreads.get(i).feed(feedingAmount);
                System.out.println("feeding tamagochi no. "+i);
            }
        }
    }

    public void eat(int tamagochiId, int foodAmount){
        tamagochiList.get(tamagochiId).setFood(foodAmount);
        printTamagochis();

    }

    public void died(int tamagochiId){
        tamagochiList.get(tamagochiId).kill();
        printTamagochis();
    }

    public void interrupted(){

    }

    public void printTamagochis(){
        System.out.println(manyNewlines);
        
        String status;
        for(int i = 0; i < tamagochiList.size(); i++){
            Tamagochi currentTamagochi = tamagochiList.get(i);
            if(currentTamagochi.isAlive()) { status = "alive"; }
            else{ status = "dead"; }
            System.out.println("Tamagochi no. "+currentTamagochi.getTamagochiId()+" is "+status+" and has "+currentTamagochi.getFoodAmount()+" food left.");
        }
    }
}