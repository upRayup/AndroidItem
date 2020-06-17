package com.example.rabbitandgrass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        final rabbitView rabbit=new rabbitView(MainActivity.this);
        rabbit.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                rabbit.bitmapX=event.getX();
                rabbit.bitmapY=event.getY();
                rabbit.invalidate();
                return true;
            }
        });
        frameLayout=findViewById(R.id.mylayout);
        frameLayout.addView(rabbit);
    }
}

