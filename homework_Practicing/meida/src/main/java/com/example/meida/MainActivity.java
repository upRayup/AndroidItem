package com.example.meida;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout ll=(FrameLayout) findViewById(R.id.frameLayout1);
        ll.addView(new MyView(this));
    }

    public class MyView extends View {
        private Bitmap bitmap;
        private ShapeDrawable drawable;

        private final int RADIUS=200;

        private Matrix matrix=new Matrix();
        public MyView(Context context) {
            super(context);
            Bitmap bitmap_source= BitmapFactory.decodeResource(getResources(),R.drawable.sea);
            bitmap=bitmap_source;
            BitmapShader shader =new BitmapShader(Bitmap.createScaledBitmap(bitmap_source,bitmap_source.getWidth(),
                    bitmap_source.getHeight(),true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            drawable=new ShapeDrawable(new OvalShape());
            drawable.getPaint().setShader(shader);
            drawable.setBounds(0,0,RADIUS*2,RADIUS*2);
        }
        @Override
        protected  void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Paint p=new Paint();
            p.setAlpha(50);
            canvas.drawBitmap(bitmap,0,0,p);
            drawable.draw(canvas);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            final int x=(int) event.getX();
            final int y=(int) event.getY();
            matrix.setTranslate(RADIUS-x,RADIUS-y);
            drawable.getPaint().getShader().setLocalMatrix(matrix);
            drawable.setBounds(x-RADIUS,y-RADIUS,x+RADIUS,y+RADIUS);
            invalidate();
            return true;
        }
    }
}
