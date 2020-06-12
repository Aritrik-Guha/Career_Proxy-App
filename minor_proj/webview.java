package com.example.minor_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class webview extends AppCompatActivity {

    Button bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar ().hide ();
        setContentView(R.layout.activity_webview);
        Intent x=getIntent();
        final String s=x.getStringExtra("value");
        final WebView wv=findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient ());
        bt1=(Button)findViewById(R.id.button8);
        bt2=(Button)findViewById(R.id.button9);
        bt3=(Button)findViewById(R.id.button10);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.getSettings().setLoadsImagesAutomatically(true);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv.loadUrl("https://www.gmail.com");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(webview.this,homepage2.class);
                i.putExtra("value",s);
                startActivity(i);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.getSettings().setLoadsImagesAutomatically(true);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv.loadUrl("https://www.google.com");
            }
        });

    }
}
