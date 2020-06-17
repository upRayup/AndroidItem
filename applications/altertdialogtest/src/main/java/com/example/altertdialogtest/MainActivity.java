package com.example.altertdialogtest;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setTitle("用户登录：");
                LayoutInflater inflater=getLayoutInflater();
                View view=inflater.inflate(R.layout.longin,null);
                builder.setView(view);
                builder.setPositiveButton("登陆",null);
                builder.setNegativeButton("退出",null);
                builder.create().show();
            }
        });
    }
}
