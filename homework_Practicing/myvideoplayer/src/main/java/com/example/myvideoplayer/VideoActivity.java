package com.example.myvideoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class VideoActivity extends AppCompatActivity {
    RecyclerView rec1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initData();
    }



    private void initView() {
        rec1=findViewById(R.id.rec1);
    }

    private void initData() {
        List<Video> videolist=new ArrayList<>();
        Video video1=new Video("紧急救援","http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4");
        videolist.add(video1);
        Video video2=new Video("玩具总动员","http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4");
        videolist.add(video2);
        Video video3=new Video("apple1","android.resource://"+getPackageName()+"/"+R.raw.apple);
        videolist.add(video3);
        Video video4=new Video("apple2","file:///storage/emulated/0/apple.mp4");
        videolist.add(video4);
        VideoAdapter adapter=new VideoAdapter(videolist);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        rec1.setLayoutManager(layoutManager);
        rec1.setAdapter(adapter);
    }


}
