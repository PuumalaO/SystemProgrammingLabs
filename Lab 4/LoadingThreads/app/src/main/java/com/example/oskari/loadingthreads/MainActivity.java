package com.example.oskari.loadingthreads;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoadingFollower.LoadingNotifier {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        textView.setMovementMethod(new ScrollingMovementMethod());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Loading loading = new Loading();
                        LoadingFollower loadingObserver = new LoadingFollower(MainActivity.this);
                        loading.addObserver(loadingObserver);
                        Thread thread =  new Thread(loading);
                        loadingObserver.setThread(String.valueOf(thread.currentThread().getId()));
                        thread.start();
                    }
                });
                thread.start();
                boolean isMain = thread.currentThread().equals( Looper.getMainLooper().getThread());
                Log.d("debug", "onClick: "+ isMain);

            }
        });
    }

    @Override
    public void onLoadingProgressed(final int loadingState, final String threadID) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                textView.append(threadID+" "+String.valueOf(loadingState)+"%\n");
                textView.scrollTo(0,0);
            }
        });
    }
}
