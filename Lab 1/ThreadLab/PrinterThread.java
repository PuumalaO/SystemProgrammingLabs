import java.time.*;
import java.time.temporal.ChronoUnit;


public class PrinterThread extends Thread{

    static int threadCount = 0;
    private int threadId;
    private boolean run = true;
    public void run(){
        threadCount++;
        threadId = threadCount;
        System.out.println("Startataan thread nro. "+threadId);
        try{
            while(run){
                LocalDateTime now = LocalDateTime.now();
                System.out.println("Kello on "+ now + ". Ilmoitti thread numero "+threadId+".");
                sleep(3000);
            }
        }

        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void stopThread(){
        run = false;
        System.out.println("Lopetetaan thread nro. " + threadId);
    }
}