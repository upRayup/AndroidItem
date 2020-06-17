package com.example.rabbitandgrass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class rabbitView extends View {
    public float bitmapX;
    public float bitmapY;

    public rabbitView(Context context) {
        super(context);
        bitmapX = 500;
        bitmapY = 300;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.rabbit);
        canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint);
        if (bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}
