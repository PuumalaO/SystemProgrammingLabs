import java.net.*;
import java.io.*;

public class FetcherThread extends Thread {

    public interface FetcherInterface{
        public void printResult(String result);
    }

    private URL address;
    private FetcherInterface callback;

    public FetcherThread(String address, FetcherInterface callbackinterface){
        
        try{
            this.address = new URL(address);
            callback = callbackinterface;
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void run(){
        try{
            BufferedReader in = new BufferedReader(
                new InputStreamReader(address.openStream()));
            String line = "";
            while (in.readLine() != null)
                line += in.readLine()+"\n";
            callback.printResult(line);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    }