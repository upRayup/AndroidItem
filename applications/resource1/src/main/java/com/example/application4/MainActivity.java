package com.example.application4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageView mimageview;
    private Button mbutton;
    private TextView mtextview;
    private String filename="/data/data/com.example.resource1/img02.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbutton=findViewById(R.id.mbutton);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mimageview=findViewById(R.id.mimageview);
                mtextview=findViewById(R.id.mtextview);
                File f=new File(filename);
                if(f.exists()){
                    Bitmap bm= BitmapFactory.decodeFile(filename);
                    mimageview.setImageBitmap(bm);
                    mtextview.setText(filename);
                }else{
                    mtextview.setText("文件不存在");
                }
            }
        });
    }
}
