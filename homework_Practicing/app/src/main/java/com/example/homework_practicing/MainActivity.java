package com.example.homework_practicing;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //1、定义对象
    RecyclerView recyclerView;
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2、绑定控件
        initView();
        //3、准备数据
        initFruits();
        //4、创建适配器
        FruitAdapter adapter = new FruitAdapter(fruitList);
        //5、让数据显示在控件上
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    //2、绑定控件-代码
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    //3、准备数据-代码
    private void initFruits() {
        for (int i = 0; i < 25; i++) {
            Fruit apple = new Fruit("陕西红富士", R.drawable.apple, "优质苹果 香甜爽口", "¥20 元 / kg");
            fruitList.add(apple);
            Fruit shiliu = new Fruit("大理石榴", R.drawable.pomegranate, " 新鲜石榴 全新上市", "¥15 元 / kg");
            fruitList.add(shiliu);
            Fruit chengzi = new Fruit("台湾茂谷柑", R.drawable.sellorange, "浓甜无渣 瓣瓣多汁", "¥49.9 元 / kg");
            fruitList.add(chengzi);
            Fruit mangguo = new Fruit("新鲜大青芒", R.drawable.mangguo, "精选大果 肥厚饱满", "¥29.9 元 / kg");
            fruitList.add(mangguo);
            Fruit putao = new Fruit("巨峰葡萄", R.drawable.putao, "爆汁超甜 新鲜采摘", "¥19.9 元 / kg");
            fruitList.add(putao);
            Fruit boluo = new Fruit("台湾菠萝", R.drawable.boluo, "香甜可口 汁水浓厚", "¥38.9 元 / kg");
            fruitList.add(boluo);
            Fruit xigua = new Fruit("早春红玉西瓜", R.drawable.xigua, "皮薄肉甜 物美价廉", "¥28.8 元 / kg");
            fruitList.add(xigua);
        }
    }

}