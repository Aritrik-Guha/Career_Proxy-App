package com.example.minor_proj;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class registerpage extends AppCompatActivity {
    private static final String TAG = "registerpage";
    Button b, b1;
    EditText e1, e2, e3, e4;
    ImageView i1;
    private FirebaseAuth auth;
    private int c=0;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        getSupportActionBar ( ).hide ( );
        setContentView (R.layout.activity_registerpage);

        auth = FirebaseAuth.getInstance ( );
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser ( );

        b = (Button) findViewById (R.id.register);
        b1 = (Button) findViewById (R.id.button25);
        e1 = (EditText) findViewById (R.id.et);
        e2 = (EditText) findViewById (R.id.et2);
        e3 = (EditText) findViewById (R.id.et3);
        e4 = (EditText) findViewById (R.id.et4);
        i1 = (ImageView) findViewById (R.id.imageView4);
        i1.setImageResource (R.drawable.logo3);

        myRef.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        b.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                String newItem = e1.getText ( ).toString ( );
                String newItem2 = e2.getText ( ).toString ( );
                String newItem3 = e3.getText ( ).toString ( );
                String newItem4 = e4.getText ( ).toString ( );


                if (newItem==" " || newItem2==" " || newItem3==" " || newItem4==" ") {
                    Toast.makeText (registerpage.this, "Empty credentials!", Toast.LENGTH_SHORT).show ( );
                }
                else if (newItem3.length ( ) < 6) {
                    Toast.makeText (registerpage.this, "Password too short!", Toast.LENGTH_SHORT).show ( );
                }
                else {
                    Log.d (TAG, "onClick: Attempting to add object to database.");
                    assert user != null;
                    String userID = user.getUid ( );
                    myRef.child ("Reg").child (String.valueOf (c)).child ("name").setValue (newItem);
                    myRef.child ("Reg").child (String.valueOf (c)).child ("email").setValue (newItem2);
                    myRef.child ("Reg").child (String.valueOf (c)).child ("pass1").setValue (newItem3);
                    myRef.child ("Reg").child (String.valueOf (c)).child ("pass2").setValue (newItem4);
                    Toast.makeText (registerpage.this, "Adding items to database...", Toast.LENGTH_SHORT).show ( );
                    c++;
                    registerUser (newItem2, newItem3);
                }
            }
        });
        b1.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (registerpage.this, registerpage.class);
                startActivity (i);
            }
        });
    }

    private void registerUser(final String email, final String password) {

        auth.createUserWithEmailAndPassword (email, password).addOnCompleteListener (registerpage.this, new OnCompleteListener<AuthResult> ( ) {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful ( )) {
                    Objects.requireNonNull (mAuth.getCurrentUser ( )).sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(registerpage.this, "Registered successfully. Please check your email for verification",
                                                Toast.LENGTH_LONG).show();
                                        startActivity (new Intent (registerpage.this, MainActivity.class));
                                        finish ( );
                                    }else{
                                        Toast.makeText(registerpage.this,  "Unknown Error in sending email..",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                } else {
                    Toast.makeText (registerpage.this, "Registration failed!", Toast.LENGTH_SHORT).show ( );
                }
            }
        });
    }
}
