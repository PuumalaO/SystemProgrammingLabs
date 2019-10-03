public class PrinterThread extends Thread{

    public interface PrinterInterface{
        void printTamagochis();
    }

    private PrinterInterface callbackInterface = null;

    public PrinterThread(PrinterInterface callback){
        callbackInterface = callback;
    }

    public void run(){
        try{    
            while(true){
                sleep(1000);
                callbackInterface.printTamagochis();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}