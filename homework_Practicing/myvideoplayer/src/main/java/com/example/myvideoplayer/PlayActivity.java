package com.example.myvideoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class PlayActivity extends AppCompatActivity {
    private TextView my_videoname;
    private VideoView my_videoview;
    private MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
        initData();
    }

    private void initView() {
        my_videoname=findViewById(R.id.my_videoname);
        my_videoview=findViewById(R.id.my_videoview);
        mediaController=new MediaController(this);
    }

    private void initData() {
        String myvideoname=getIntent().getStringExtra("videoname");
        String myvideourl=getIntent().getStringExtra("videourl");
        my_videoname.setText(myvideoname);
        my_videoview.setVideoPath(myvideourl);
        my_videoview.setMediaController(mediaController);
        mediaController.setMediaPlayer(my_videoview);
        my_videoview.start();
    }
}
