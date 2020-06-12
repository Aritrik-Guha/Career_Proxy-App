package com.example.minor_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ImageView iv;
    private static int SPLASH_SCREEN_TIME_OUT=9000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar ().hide ();
        setContentView(R.layout.activity_splash);
        TextView tv=(TextView)findViewById(R.id.textView33);
        TextView tv2=(TextView)findViewById(R.id.textView40);
        TextView tv3=(TextView)findViewById(R.id.textView41);
        ImageView iv1;
        iv1=(ImageView)findViewById(R.id.imageView);
        iv1.setImageResource(R.drawable.hp);
        tv3.setScaleX(0f);
        tv3.setScaleY(0f);
        tv.animate().scaleXBy(4f).scaleYBy(4f).alpha(0f).setDuration(5000);
        tv2.animate().scaleXBy(4f).scaleYBy(4f).alpha(0f).setDuration(5000);
        iv1.animate().scaleXBy(4f).scaleYBy(4f).alpha(0f).setDuration(5000);
        tv3.animate().alpha(1f).scaleXBy(1f).scaleYBy(1f).setDuration(4000);
        new Handler ().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1=new Intent(Splash.this,
                        MainActivity.class);
                startActivity(i1);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}