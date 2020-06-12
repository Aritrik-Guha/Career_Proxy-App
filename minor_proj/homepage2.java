package com.example.minor_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homepage2 extends AppCompatActivity {

    TextView e24;
    Button b1,b30,b31,b32,b26,b27,b28,b29,bot;
    ImageView v6,v7,v9,v10,v11;
    private FirebaseAuth auth;
    String email;
    String personEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar ().hide ();
        setContentView(R.layout.activity_homepage2);
        Intent x=getIntent();
        final String s=x.getStringExtra("Email");
        e24=(TextView)findViewById(R.id.textView3);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            //Toast.makeText(MainActivity.this,personName + personEmail ,Toast.LENGTH_SHORT).show();
        }
        if (s!=null)
        {
            e24.setText("Welcome"+" "+s);
        }
        else if (user!=null) {
            email = user.getEmail();
            e24.setText("Welcome"+" "+email);
        }
        else{
            e24.setText ("Welcome"+" "+personEmail);
        }



        v6=(ImageView)findViewById(R.id.imageView6);
        v7=(ImageView)findViewById(R.id.imageView7);
        v9=(ImageView)findViewById(R.id.imageView9);
        v11=(ImageView)findViewById(R.id.imageView11);
        v11.setImageResource(R.drawable.end5);
        v6.setImageResource(R.drawable.news1);
        v7.setImageResource(R.drawable.news2);
        v9.setImageResource(R.drawable.news3);
        v7.setTranslationX (-2000f);
        v7.setTranslationY (0f);
        v9.setTranslationX (-2000f);
        v9.setTranslationY (0f);
        b1=(Button)findViewById(R.id.button);
        b30=(Button)findViewById(R.id.button30);
        b31=(Button)findViewById(R.id.button31);
        b32=(Button)findViewById(R.id.button32);
        b26=(Button)findViewById(R.id.button26);
        b27=(Button)findViewById(R.id.button27);
        b28=(Button)findViewById(R.id.button28);
        b29=(Button)findViewById(R.id.button29);
        bot=(Button)findViewById (R.id.Bot);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(homepage2.this,MainActivity.class);
                auth.signOut ();
                Toast.makeText(homepage2.this,"Signing out...",Toast.LENGTH_SHORT).show();
                startActivity(i1);
            }
        });
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(homepage2.this,webview.class);
                startActivity(i2);
            }
        });
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(homepage2.this,randomguess.class);
                startActivity(i3);
            }
        });
        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(homepage2.this,tictactoe.class);
                startActivity(i4);
            }
        });
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5=new Intent(homepage2.this,school_list.class);
                startActivity(i5);
            }
        });
        b27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6=new Intent(homepage2.this,college_list.class);
                startActivity(i6);
            }
        });
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i7=new Intent(homepage2.this,school_board.class);
                startActivity(i7);
            }
        });
        b29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i8=new Intent(homepage2.this,college_degree.class);
                startActivity(i8);
            }
        });
        v6.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                v6.animate ().translationXBy (-2000f).translationYBy (0f);
                v7.animate ().translationXBy (2000f).translationYBy (0f);
            }
        });
        v7.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                v7.animate ().translationXBy (-2000f).translationYBy (0f);
                v9.animate ().translationXBy (2000f).translationYBy (0f);
            }
        });
        v9.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                v9.animate ().translationXBy (-2000f).translationYBy (0f);
                v6.animate ().translationXBy (2000f).translationYBy (0f);
            }
        });

        if(s!=null) {
            bot.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent ibot = new Intent (homepage2.this, bot.class);
                    ibot.putExtra ("name",s );
                    startActivity (ibot);
                }
            });
        }
        else {
            bot.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent ibot = new Intent (homepage2.this, bot.class);
                    ibot.putExtra ("name",email );
                    startActivity (ibot);
                }
            });
        }
    }
}

