package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView=(WebView)findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://m.weather.com.cn/mweather/");
        webView.setInitialScale(57*3);
        Button bj=(Button)findViewById(R.id.bj);
        bj.setOnClickListener(this);
        Button sh=(Button)findViewById(R.id.sh);
        sh.setOnClickListener(this);
        Button heb=(Button)findViewById(R.id.heb);
        heb.setOnClickListener(this);
        Button cc=(Button)findViewById(R.id.cc);
        cc.setOnClickListener(this);
        Button sy=(Button)findViewById(R.id.sy);
        sy.setOnClickListener(this);
        Button gz=(Button)findViewById(R.id.gz);
        gz.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bj:
                openUrI("101010100");
                break;
            case R.id.sh:
                openUrI("101020100");
                break;
            case R.id.heb:
                openUrI("101050101");
                break;
            case R.id.cc:
                openUrI("101060101");
                break;
            case R.id.sy:
                openUrI("101070101");
                break;
            case R.id.gz:
                openUrI("101280101");
                break;
        }
    }
    private void openUrI(String id){
        webView.loadUrl("http://m.weather.com.cn/mweather/"+id+".shtml");
    }
}
