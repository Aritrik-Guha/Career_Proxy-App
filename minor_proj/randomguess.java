package com.example.minor_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class randomguess extends AppCompatActivity {

    TextView t1, t2;
    Button b33, b34;
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar ().hide ();
        setContentView(R.layout.activity_randomguess);
        Intent x=getIntent();
        final String s=x.getStringExtra("value");
        t1 = (TextView) findViewById(R.id.textView24);
        t2 = (TextView) findViewById(R.id.textView43);
        b33 = (Button) findViewById(R.id.button33);
        b34 = (Button) findViewById(R.id.button34);
        et1 = (EditText) findViewById(R.id.editText);
        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(randomguess.this, homepage2.class);
                i1.putExtra("value",s);
                startActivity(i1);
            }
        });
        b34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(randomguess.this, randomguess.class);
                startActivity(i2);
            }
        });
    }
    int t=5;
    Random r = new Random();
    int s = r.nextInt(30)+0;
    public void display(View view) {
        t=t-1;
        int c = 0;
        String str = et1.getText().toString();
        int n = Integer.parseInt(str);
        if (n > 30 || n < 0) {
            Toast.makeText(randomguess.this, "Please stay within limits!!", Toast.LENGTH_SHORT).show();
        } else if (n == s) {
            s = r.nextInt(30)+0;
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
            c = c + 1;
            t1.setText("Player :" + " " + c);
            t2.setText("You have "+t+" tries left");
            //t2.setBackgroundColor(Color.WHITE);
        } else if (n < s) {
            Toast.makeText(this, "Greater", Toast.LENGTH_SHORT).show();
            t2.setText("You have "+t+" tries left");
            t2.setBackgroundColor(Color.RED);
        } else if (n > s) {
            Toast.makeText(this, "Lesser", Toast.LENGTH_SHORT).show();
            t2.setText("You have "+t+" tries left");
            t2.setBackgroundColor(Color.RED);
        }
        if(t==0)
        {
            Toast.makeText(randomguess.this, "You have failed.The correct answer was " + s, Toast.LENGTH_LONG).show();
            c = c - 1;
            t1.setText("Player :" + " " + c);
            t = 5;
            t2.setText("You have " + t + " " + "tries left.");
        }
    }
}
