package com.example.minor_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class dps extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    TextView tvcheck2,t1,t2,t3,t4;
    Button b1,b2,b3;
    ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        getSupportActionBar ().hide ();
        setContentView (R.layout.activity_dps);
        String x="a";
        Bundle extras=getIntent ().getExtras ();
        if (extras!=null){
            x=extras.getString ("value4");
        }
        t1=findViewById (R.id.dps1);
        t2=findViewById (R.id.dps2);
        t3=findViewById (R.id.dps3);
        t4=findViewById (R.id.dps4);
        tvcheck2=findViewById (R.id.tvcheck);
        iv2=findViewById (R.id.schoolimage);
        b1=findViewById (R.id.dpsb1);
        b2=findViewById (R.id.dpsb2);
        b3=findViewById (R.id.dpsb3);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child(x);

        final String finalX = x;
        myRef.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists ( )) {
                    tvcheck2.setText (finalX);
                    String y=dataSnapshot.child ("Url").getValue ().toString ();
                    t1.setText (dataSnapshot.child ("Board").getValue ().toString ());
                    t2.setText (dataSnapshot.child ("Description").getValue ().toString ());
                    t3.setText (dataSnapshot.child ("Facilities").getValue ().toString ());
                    t4.setText (dataSnapshot.child ("Location").getValue ().toString ());
                    Picasso.get ().load(y).into (iv2);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent (dps.this,homepage2.class);
                startActivity (i1);
            }
        });


        b2.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent (dps.this,webview.class);
                startActivity (i2);
            }
        });

        b3.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Toast.makeText (dps.this,"This functionality is not active now. Coming soon!!",Toast.LENGTH_LONG).show ();
            }
        });

    }
}
