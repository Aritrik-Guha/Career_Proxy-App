package com.example.minor_proj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class registered extends AppCompatActivity {

    private static final String TAG = "registered";
    Button b, b1;
    EditText e1, e2, e3, e4;
    ImageView i1;
    private FirebaseAuth auth;
    private int RC_SIGN_IN = 1;
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    //private  String TAG = "MainActivity";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        getSupportActionBar ( ).hide ( );
        setContentView (R.layout.activity_registered);

        auth = FirebaseAuth.getInstance ( );
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser ( );
        signInButton = findViewById(R.id.sign_in_button);

        b = (Button) findViewById (R.id.register);
        b1 = (Button) findViewById (R.id.button25);
        e1 = (EditText) findViewById (R.id.et1);
        e2 = (EditText) findViewById (R.id.et2);
        e3 = (EditText) findViewById (R.id.et);
        e4 = (EditText) findViewById (R.id.et4);
        i1 = (ImageView) findViewById (R.id.imageView4);
        i1.setImageResource (R.drawable.logo3);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(registered.this, gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


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
                final String newItem = e1.getText ( ).toString ( );
                final String newItem2 = e2.getText ( ).toString ( );
                final String newItem3 = e3.getText ( ).toString ( );
                final String newItem4 = e4.getText ( ).toString ( );


                if (newItem.equals ("") || newItem2.equals ("") || newItem3.equals ("") || newItem4.equals ("")) {
                    Toast.makeText (registered.this, "Empty credentials!", Toast.LENGTH_SHORT).show ( );
                }
                else if (newItem3.length ( ) < 6) {
                    Toast.makeText (registered.this, "Password too short!", Toast.LENGTH_SHORT).show ( );
                }
                else {
                   // registerUser (newItem2, newItem3);
                    auth.createUserWithEmailAndPassword (newItem2, newItem3).addOnCompleteListener (registered.this, new OnCompleteListener<AuthResult> ( ) {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful ( )) {
                                mAuth.getCurrentUser ( ).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(registered.this, "Registered successfully. " +
                                                            "Please check your email " + "for verification", Toast.LENGTH_SHORT).show();
                                                    Log.d (TAG, "onClick: Attempting to add object to database.");
                                                    assert user != null;
                                                    String userID = myRef.push ( ).getKey ();
                                                    myRef.child (userID).child ("name").setValue (newItem);
                                                    myRef.child (userID).child ("email").setValue (newItem2);
                                                    myRef.child (userID).child ("password").setValue (newItem3);
                                                    myRef.child (userID).child ("mobile_no").setValue (newItem4);
                                                    startActivity (new Intent (registered.this, MainActivity.class));
                                                }else{
                                                    Toast.makeText(registered.this,  "Unknown Error in sending email..",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText (registered.this, "Registration failed!", Toast.LENGTH_SHORT).show ( );
                            }
                        }
                    });
                }
            }
        });
        b1.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (registered.this, registered.class);
                startActivity (i);
            }
        });
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{

            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(registered.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            Toast.makeText(registered.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        //check if the account is null
        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(registered.this, "Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent im=new Intent (registered.this,homepage2.class);
                        startActivity (im);
                        updateUI(user);
                    } else {
                        Toast.makeText(registered.this, "Failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
        else{
            Toast.makeText(registered.this, "acc failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser fUser){
        //btnSignOut.setVisibility(View.VISIBLE);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            //Toast.makeText(MainActivity.this,personName + personEmail ,Toast.LENGTH_SHORT).show();
        }

    }
}


