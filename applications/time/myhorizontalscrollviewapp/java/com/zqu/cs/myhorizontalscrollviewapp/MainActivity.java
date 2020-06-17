package com.zqu.cs.myhorizontalscrollviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //定义控件变量
    private LinearLayout myLinearLayout ;	//定义LinearLayout控件变量

    private int[] image = {
            R.drawable.cp, R.drawable.db0, R.drawable.db2, R.drawable.db4, R.drawable.java0, R.drawable.java2, R.drawable.qt1, R.drawable.qt3, R.drawable.qt4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);		//对linearLayout控件引用
        for (Integer id : image) {					//遍历得到每一个图片ID
            myLinearLayout.addView(addImage(id));			//(b)
        }


    }

    //(b) 将ID对应图片加入linearLayout控件中
    private View addImage(Integer id) {
        LinearLayout layout1 = new LinearLayout(getApplicationContext());	//动态创建LinearLayout控件
        layout1.setLayoutParams(new LinearLayout.LayoutParams(293, 404));	//设置LinearLayout控件宽高
        layout1.setGravity(Gravity.CENTER);				//LinearLayout中的图片对中
        ImageView imageView1 = new ImageView(getApplicationContext());	//动态创建ImageView控件
        imageView1.setLayoutParams(new LinearLayout.LayoutParams(273, 384));
        imageView1.setImageResource(id);		//将ID作为ImageView控件图片
        layout1.addView(imageView1);		//将ImageView控件加入到LinearLayout控件
        return layout1;
    }
}
