package com.example.oskari.androidhttpfetcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements FetcherThread.FetcherInterface {

    private TextView dataLabel;
    private Button searchButton;
    private EditText input;
    private FetcherThread fetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataLabel = findViewById(R.id.dataLabel);
        input = findViewById(R.id.input);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().length() > 0){
                    try{
                        URL url = new URL(input.getText().toString());
                        fetcher = new FetcherThread(MainActivity.this, url);
                        fetcher.start();
                    }catch (MalformedURLException e){
                        Log.d("mydebug", "onClick: malformedurl "+e);
                        Toast.makeText(MainActivity.this, "Not a valid url", Toast.LENGTH_SHORT);
                    }
                }
            }
        });
    }

    protected void updateUI(){
        dataLabel.setText(fetcher.getLine().toString());
    }

        @Override
        public void printResult(){
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateUI();
                }
            });
        }
}
