package com.example.annotaatioyritys6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.textView)
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runOnTheBack();
    }

    @Background
    public void runOnTheBack(){
        Thread thread = new Thread();
        Log.d("debug", "runOnTheBack: "+thread.currentThread().getName());
    }

    @UiThread
    public void updateSomethingOnTheUI()
    {
        textview.setText("UPDATE UI");
    }

    @Click(R.id.button)
    void onButtonClick() {
        updateSomethingOnTheUI();
    }
}
