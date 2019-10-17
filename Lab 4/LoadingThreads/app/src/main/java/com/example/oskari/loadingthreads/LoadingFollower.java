package com.example.oskari.loadingthreads;

import java.util.Observable;
import java.util.Observer;

public class LoadingFollower implements Observer {
    public interface LoadingNotifier{

        void onLoadingProgressed(int loadingState, String threadID);
    }
    private String threadID;
    private LoadingNotifier callbackInterface;

    public LoadingFollower(LoadingNotifier callbackInterface) {
        this.callbackInterface = callbackInterface;
    }

    @Override
    public void update(Observable o, Object arg) {
        callbackInterface.onLoadingProgressed((Integer) arg, threadID);
    }

    public void setThread(String threadID){
        this.threadID = threadID;
    }
}
