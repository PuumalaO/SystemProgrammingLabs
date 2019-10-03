

import java.util.concurrent.ThreadLocalRandom;
public class TamagochiThread extends Thread{

    public interface TamagochiReporterInterface{
        void eat(int tamagochiId, int foodAmount);
        void died(int tamagochiId);
        void interrupted();
    }

    static int tamagochiCount = -1;
    static int tamagochisAwake = 0;
    private int tamagochiId;
    private int foodAmount;
    private int maxFood;
    private TamagochiReporterInterface callbackInterface = null;

    public TamagochiThread(int startFood, int maxFood, TamagochiReporterInterface callback){
        callbackInterface = callback;
        this.foodAmount = startFood;
        this.maxFood = maxFood;
        tamagochiCount++;
        tamagochiId = tamagochiCount;
    }

    public void run(){

        try {
            int randomTime = ThreadLocalRandom.current().nextInt(1, 3000);
            java.lang.Thread.sleep(randomTime);
            tamagochisAwake++;

        } catch (Exception e) {
            e.printStackTrace();
        }

        try{    
            while(foodAmount >= 0 && foodAmount <= maxFood){
                callbackInterface.eat(tamagochiId, foodAmount);
                foodAmount--;
                sleep(1000);
            }
        }catch(Exception e){
            e.printStackTrace();
            callbackInterface.interrupted();
        }
        callbackInterface.died(tamagochiId);
    }

    public void feed(int feedingAmount){
        foodAmount+=feedingAmount;
    }

    public int getTamagochiId(){
        return this.tamagochiId;
    }

}