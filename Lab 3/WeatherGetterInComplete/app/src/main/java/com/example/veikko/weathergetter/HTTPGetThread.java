package com.example.veikko.weathergetter;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class HTTPGetThread extends Thread {

    public interface OnRequestDoneInterface
    {
        // This method is called back in background thread.
        public void onRequestDone(String data);
    }

    private URL url = null;
    private OnRequestDoneInterface callback;

    public HTTPGetThread(String url, OnRequestDoneInterface callback) {
        try{
            this.url = new URL(url);
        }catch (MalformedURLException e){
            Log.d("HTTPGetThread", "HTTPGetThread: "+e);
        }
        this.callback = callback;
    }

    @Override
    public void run() {
        super.run();

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            line = in.readLine();
            Log.d("HTTPGetThread", "run: "+line);
            in.close();
            callback.onRequestDone(line);

        }catch(MalformedURLException e){
            Log.d("HTTPGetThread", "MalfromedURL: "+e);
        }catch (IOException e) {
            Log.d("HTTPGetThread", "IOException: "+e);
        }
        }

    }
