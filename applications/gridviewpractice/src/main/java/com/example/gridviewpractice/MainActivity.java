package com.example.gridviewpractice;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    private int [] imageid=new int[]{
            R.drawable.img01,R.drawable.img02,
            R.drawable.img03,R.drawable.img04,
            R.drawable.img05,R.drawable.img06,
            R.drawable.img07,R.drawable.img08,
            R.drawable.img09,R.drawable.img10,
            R.drawable.img11,R.drawable.img12};
    final String [] filename=new String[]{
            "img01.jpg","img02.jpg","img03.jpg","img04.jpg",
            "img05.jpg","img06.jpg","img07.jpg","img08.jpg",
            "img09.jpg","img10.jpg","img11.jpg","img12.jpg"};
    private ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Map<String,Object>> listitems=new ArrayList<>();
        for (int i=0;i<imageid.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("image",imageid[i]);
            map.put("title",filename[i]);
            listitems.add(map);
        }
        final SimpleAdapter adapter=new SimpleAdapter(this,listitems,
                R.layout.items,new String[]{"title","image"},new int[]{R.id.title,R.id.image});
        imageSwitcher=findViewById(R.id.imageSwitcher1);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                return imageView;
            }
        });
        imageSwitcher.setImageResource(imageid[6]);
        GridView gridView=findViewById(R.id.gridView1);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageSwitcher.setImageResource(imageid[position]);
            }
        });
    }
}
