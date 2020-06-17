package com.example.minigame;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    TextView score_show;    //分数展示
    TextView bestscore_show;    //最高分展示
    GameView gv;    //子控件布局
    Button new_game;    //新游戏按钮
    Button rollback;    //反悔按钮
    SharedPreferences.Editor editor=null;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int num=msg.arg1;
            score_show.setText(num+"");
        }
    };
    Handler handler1=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int num=msg.arg1;
            bestscore_show.setText(num+"");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score_show=(TextView)findViewById(R.id.tv_score_show);
        gv=(GameView)findViewById(R.id.gv_show);
        new_game=(Button) findViewById(R.id.tv_newganme);
        bestscore_show=(TextView)findViewById(R.id.tv_bestscore_show);
        //判断是不是第一次运行
        iffirstrun();
        bestscore_show.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                editor=getSharedPreferences("mybestscore",0).edit();
                editor.putString("bestscore",Integer.toString(gv.getBscore()));
                editor.commit();
            }
        });
        new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.GameStart();
                gv.score=0;
            }
        });
        rollback=(Button)findViewById(R.id.tv_rockback);
        rollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.Gameback();
                System.out.println("我反悔了");
            }
        });
        //定时器，定时刷新分数
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.arg1=gv.score;
                handler.sendMessage(msg);
            }
        },80,150);
        score_show.setText(100+"");

        //定时器，定时刷新最高分数
        Timer timer1=new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.arg1=gv.bscore;
                handler1.sendMessage(msg);
            }
        },80,150);
    }

    private void iffirstrun() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        if (isFirstRun){//第一次运行就创建最高分文件
            Log.d("debug", "第一次运行");
            editor=getSharedPreferences("mybestscore",0).edit();
            editor.putString("bestscore",bestscore_show.getText().toString());
            editor.commit();
            ed.putBoolean("isFirstRun", false);
            ed.commit();
        }else{
            Log.d("debug", "不是第一次运行");
            String bestscore=getSharedPreferences("mybestscore",0).getString("bestscore","");
            bestscore_show.setText(bestscore);
            gv.setBscore(Integer.parseInt(bestscore));
        }
    }
}
