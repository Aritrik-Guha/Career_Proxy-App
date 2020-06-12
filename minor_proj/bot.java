package com.example.minor_proj;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class bot extends AppCompatActivity {
    WebView wb;
    TextView tb;
    Button bb;
    ImageView ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar ().hide ();
        setContentView(R.layout.activity_bot);
        wb=findViewById(R.id.webView2);
        tb=findViewById (R.id.tbot);
        bb=findViewById (R.id.bbot);
        ib=findViewById (R.id.ibot);
        ib.setImageResource (R.drawable.logo2);
        Intent ib=getIntent ();
        String x=ib.getStringExtra ("name");
        tb.setText ("Hi "+x+", Dr. Sheldon Cooper at your service!!");
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl("https://bot.dialogflow.com/fe6b5b7e-4869-44e9-96f1-6a19d27c81ad");
        wb.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        bb.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent (bot.this,homepage2.class);
                startActivity (i2);
            }
        });
    }
}