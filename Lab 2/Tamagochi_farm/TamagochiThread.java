public class TamagochiThread extends Thread{

    public interface TamagochiReporterInterface{
        void eat(int tamagochiId, int foodAmount);
        void died(int tamagochiId);
        void interrupted();
    }

    static int tamagotchiCount = -1;
    private int tamagotchiId;
    private int foodAmount;
    private int maxFood;
    private TamagochiReporterInterface callbackInterface = null;

    public TamagochiThread(int startFood, int maxFood, TamagochiReporterInterface callback){
        callbackInterface = callback;
        this.foodAmount = startFood;
        this.maxFood = maxFood;
        tamagotchiCount++;
        tamagotchiId = tamagotchiCount;
    }

    public void run(){
        try{    
            while(foodAmount >= 0 && foodAmount <= maxFood){
                callbackInterface.eat(tamagotchiId, foodAmount);
                foodAmount--;
                sleep(500);
            }
        }catch(Exception e){
            e.printStackTrace();
            callbackInterface.interrupted();
        }
        callbackInterface.died(tamagotchiId);
    }

    public void feed(){

    }

}