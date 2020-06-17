package com.example.homework_practicing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    //1、定义对象
    private TextView textView;
    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView intro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //2、绑定控件
        initview();
        //3、接收 Intent 跳转传递过来的数据--位置
        Intent intent = getIntent();
        Fruit fruit = (Fruit) getIntent().getSerializableExtra("myposition");
        //4、将序列化保存的水果信息显示在控件中
        image.setImageResource(fruit.getImageId());
        name.setText("水果:"+fruit.getName());
        price.setText("价格:"+fruit.getPrice());
        intro.setText("特色:"+fruit.getIntro());
    }
    //2、绑定控件-代码
    private void initview() {
        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        intro = (TextView) findViewById(R.id.intro);
    } }
