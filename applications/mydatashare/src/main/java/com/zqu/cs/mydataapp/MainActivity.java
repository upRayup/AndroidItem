package com.zqu.cs.mydataapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by ouyangshen on 2017/10/1.
 */
public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //连接主界面

        findViewById(R.id.btn_share_write).setOnClickListener(this);    //给按钮加监听事件
        findViewById(R.id.btn_share_read).setOnClickListener(this);
        findViewById(R.id.btn_sqlite_create).setOnClickListener(this);
        findViewById(R.id.btn_sqlite_write).setOnClickListener(this);
        findViewById(R.id.btn_sqlite_read).setOnClickListener(this);
        findViewById(R.id.btn_file_basic).setOnClickListener(this);
        findViewById(R.id.btn_text_write).setOnClickListener(this);
        findViewById(R.id.btn_text_read).setOnClickListener(this);
        findViewById(R.id.btn_image_write).setOnClickListener(this);
        findViewById(R.id.btn_image_read).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_share_write) {
            Intent intent = new Intent(this, ShareWriteActivity.class); //意图跳转
            startActivity(intent);
        } else if (v.getId() == R.id.btn_share_read) {
            Intent intent = new Intent(this, ShareReadActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_sqlite_create) {
            Intent intent = new Intent(this, DatabaseActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_sqlite_write) {
            Intent intent = new Intent(this, SQLiteWriteActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_sqlite_read) {
            Intent intent = new Intent(this, SQLiteReadActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_file_basic) {
            Intent intent = new Intent(this, FileBasicActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btn_text_write) {
            Intent intent = new Intent(this, TextWriteActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_text_read) {
            Intent intent = new Intent(this, TextReadActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_image_write) {
            Intent intent = new Intent(this, ImageWriteActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_image_read) {
            Intent intent = new Intent(this, ImageReadActivity.class);
            startActivity(intent);

        }
    }

}
