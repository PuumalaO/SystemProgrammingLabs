package com.example.oskari.androidhttpfetcher;

import android.util.Log;

import java.net.*;
import java.io.*;

public class FetcherThread extends Thread {

    public interface FetcherInterface{
        public void printResult();
    }

    public String getLine() {
        return line;
    }

    private String line;
    private FetcherInterface callback;
    private URL address;

    public FetcherThread(FetcherInterface callbackinterface, URL url){
        address = url;
        callback = callbackinterface;

    }


    public void run(){

        try{

            BufferedReader in = new BufferedReader(
                new InputStreamReader(address.openStream()));
            line = "";
            while (in.readLine() != null)
                line += in.readLine();
                Log.d("mydebug", "line: "+line);
                Log.d("mydebug", "run: TOIMIIKO TÄÄ PASKA?");
                line += in.readLine()+"\n";
            callback.printResult();
        }catch(Exception e){
            Log.d("mydebug", "run malformedurltms: "+e);
        }
    }
    }