package com.example.bravo.light;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomBackgroundColor();
    }


    //Creating random color in hexadecimal value
    public void randomBackgroundColor() {
        View view = this.getWindow().getDecorView(); //Current screen

        char[] colorCode = new char[6];
        Random generator = new Random();
        for (int i = 0; i <= 5; i++) {
            int hexNum = generator.nextInt(16);
            colorCode[i] = Character.forDigit(hexNum, 16); //From 0...F

        }
        String colorCodeString = "#" + new String(colorCode); //hex color code

        int colorNum = Color.parseColor(colorCodeString); //int representation of color
        view.setBackgroundColor(colorNum);
        view.invalidate(); //Reload view
        updateTextColorCode(colorCodeString,colorNum); //Update hex code on screen
        updateColorName(colorNum); //Update color name on screen

    }



    //Repeating every half second
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run()
        {
            randomBackgroundColor();
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


    public void updateTextColorCode(String hexcode, int colorNum) {
        TextView colorText = (TextView) findViewById(R.id.colorText);
        colorText.setText(hexcode); //Update text content
        colorText.setTextColor(colorNum);
    }

    public void updateColorName(int colorNum) {
        ColorUtils color = new ColorUtils();
        String colorName = color.getColorNameFromHex(colorNum);
        TextView colorNameText = (TextView) findViewById(R.id.colorName);
        colorNameText.setText(colorName);
        colorNameText.setTextColor(colorNum);
        //Log.i("checking",colorName);
    }
}
