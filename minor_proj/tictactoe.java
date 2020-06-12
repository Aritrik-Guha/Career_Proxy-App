package com.example.minor_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tictactoe extends AppCompatActivity {

    private Button[][] buttons=new Button[3][3];
    private boolean p1t=true;
    private int roundCount,p1point,p2point;
    private TextView p1,p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar ().hide ();
        setContentView(R.layout.activity_tictactoe);
        Intent x=getIntent();
        final String s=x.getStringExtra("value");
        p1=(TextView)findViewById(R.id.textView3);
        p2=(TextView)findViewById(R.id.textView4);
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                String bid="tbutton"+i+j;
                int rid=getResources().getIdentifier(bid,"id",getPackageName());
                buttons[i][j]=findViewById(rid);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!((Button) view).getText().toString().equals(""))
                        {
                            return;
                        }
                        if (p1t)
                        {
                            ((Button)view).setText("X");
                            ((Button)view).setBackgroundColor (Integer.parseInt ("red"));
                        }
                        else
                        {
                            ((Button)view).setText("O");
                            ((Button)view).setBackgroundColor (Integer.parseInt ("blue"));
                        }
                        roundCount++;
                        if(cfW())
                        {
                            if(p1t)
                            {
                                p1point++;
                                Toast.makeText(tictactoe.this,"Player 1 wins..",Toast.LENGTH_SHORT).show();
                                p1.setText("Player 1 : "+p1point);
                                for (int i=0;i<3;i++)
                                {
                                    for (int j=0;j<3;j++)
                                    {
                                        buttons[i][j].setText("");
                                    }
                                }
                                roundCount=0;
                                p1t=true;
                            }
                            else
                            {
                                p2point++;
                                Toast.makeText(tictactoe.this,"Player 2 wins..",Toast.LENGTH_SHORT).show();
                                p2.setText("Player 2 : "+p2point);
                                for (int i=0;i<3;i++)
                                {
                                    for (int j=0;j<3;j++)
                                    {
                                        buttons[i][j].setText("");
                                    }
                                }
                                roundCount=0;
                                p1t=true;
                            }
                        }
                        else if(roundCount==9)
                        {
                            Toast.makeText(tictactoe.this,"Draw",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            p1t=!p1t;
                        }
                    }
                    private boolean cfW()
                    {
                        String[][] field=new  String[3][3];
                        for (int i=0;i<3;i++) {
                            for (int j = 0; j < 3; j++) {
                                field[i][j] = buttons[i][j].getText().toString();
                            }
                        }
                        for (int i=0;i<3;i++)
                        {
                            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
                            {
                                return true;
                            }
                        }
                        for (int i=0;i<3;i++)
                        {
                            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
                            {
                                return true;
                            }
                        }
                        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
                        {
                            return true;
                        }
                        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
                        {
                            return true;
                        }
                        return false;
                    }

                });
            }
        }
        Button br=(Button)findViewById(R.id.tbutton);
        Button br2=(Button)findViewById(R.id.tbutton2);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<3;i++)
                {
                    for (int j=0;j<3;j++)
                    {
                        buttons[i][j].setText("");
                    }
                }
                roundCount=0;
                p1t=true;
                p1.setText("Player 1 : 0");
                p2.setText("Player 2 : 0");
            }
        });
        br2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(tictactoe.this,homepage2.class);
                i1.putExtra("value",s);
                startActivity(i1);
            }
        });
    }
}
