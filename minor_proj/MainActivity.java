package com.example.minor_proj;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login,register,guest,fp;
    ImageView i3;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar ().hide ();
        setContentView(R.layout.activity_main);


        email = findViewById(R.id.et1);
        fp=findViewById (R.id.button4);
        password = findViewById(R.id.et2);
        login = findViewById(R.id.sb1);
        register=findViewById (R.id.sb2);
        guest=findViewById (R.id.bt3);
        i3=findViewById (R.id.imageView3);
        i3.setImageResource (R.drawable.logo3);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(MainActivity.this, "Fill Login Details", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(txt_email , txt_password);
                }
            }
        });

        register.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent (MainActivity.this,registered.class);
                startActivity (i3);
            }
        });

        guest.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent (MainActivity.this,homepage2.class);
                i1.putExtra ("Email","Guest");
                startActivity (i1);
            }
        });

        fp.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText ( ).toString ( );
                if (txt_email != null) {
                    Intent i2 = new Intent (MainActivity.this, forgotpswd.class);
                    startActivity (i2);
                }
            }
        });
    }

    private void loginUser(String email, String password) {

       auth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult> () {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Sign In Error", Toast.LENGTH_SHORT).show();
                } else {
                    if(Objects.requireNonNull (auth.getCurrentUser ( )).isEmailVerified()){
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, homepage2.class));
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Please verify your email address"
                                , Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
