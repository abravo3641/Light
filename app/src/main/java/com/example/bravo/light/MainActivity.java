package com.example.bravo.light;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomColors();
    }


    //Creating random color in hexadecimal value
    public void randomColors() {
        View view;
        view = this.getWindow().getDecorView();
        char[] colorCode = new char[6];
        Random generator = new Random();
        for (int i = 0; i <= 5; i++) {
            int hexNum = generator.nextInt(16);
            colorCode[i] = Character.forDigit(hexNum, 16);

        }
        String color = "#" + new String(colorCode);
        Log.d("myTag", "here");
        view.setBackgroundColor(Color.parseColor(color));
        view.invalidate();
    }



    //Repeating every one second
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run()
        {
            randomColors();
            handler.postDelayed(runnable, 500);
        }
    };

    //When start is clicked
    public void onStartClick(View view) {
        handler.postDelayed(runnable, 500);
    }

    //When stop is clicked
    public void onStopClick(View view) {
        handler.removeCallbacks(runnable);
    }
}
