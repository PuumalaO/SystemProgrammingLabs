package com.example.mygreatflickrapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import androidx.annotation.Nullable;

public class IntroductionActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Welcome!", "You can search images using the search bar at the top. Press the search button to get 10 images", R.drawable.ic_done_white, Color.RED));
        setVibrate(true);
        setVibrateIntensity(30);
        setFadeAnimation();
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.

        final Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
