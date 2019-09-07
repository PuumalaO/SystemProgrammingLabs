import java.util.Scanner;

import java.util.ArrayList;

class TamagochiFarm implements TamagochiThread.TamagochiReporterInterface{
   
    public final static int startFood = 10;
    public final static int maxFood = 20;
    public final int tamagochis = 7;
    private boolean gameOver = false;
    private ArrayList<TamagochiThread> tamagochiThreads = new ArrayList<TamagochiThread>();
    //Maybe implement ArrayList for tamagochi states?
    public static void main(String args[]){
        TamagochiFarm game = new TamagochiFarm();
        game.wakeTamagochis(startFood, maxFood, game);
        game.gameLoop();

    }

    public void wakeTamagochis(int startFood, int maxFood, TamagochiFarm callback){
        
        System.out.println(tamagochis+" Tamagochis wake up and are hungry. Feed them as quickly as you can!");
        
        for(int i = 0; i <tamagochis; i++){
            TamagochiThread tamagochi = new TamagochiThread(startFood, maxFood, callback);
            tamagochiThreads.add(tamagochi);
                
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
            if(command.equals("feed")){
                System.out.println("feeding");
            }
        }
    }

    public void eat(int tamagochiId, int foodAmount){
        System.out.print("\r");
        System.out.print("Tamagochi no. "+tamagochiId+" ate "+foodAmount+" food left.");
    }

    public void died(int tamagochiId){
        System.out.println("Tamagochi no. "+tamagochiId+" died");
    }

    public void interrupted(){

    }
}