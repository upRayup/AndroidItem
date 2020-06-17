package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Menuexample extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_ex,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView label = (TextView)findViewById(R.id.label);
        switch (item.getItemId()) {
            case R.id.main_menu_0:
                label.setText(" 打印，菜单 ID：" + item.getItemId());
                return true;
            case R.id.main_menu_1:
                label.setText(" 新建，菜单 ID：" + item.getItemId());
                return true;
            case R.id.main_menu_2:
                label.setText(" 邮件，菜单 ID：" + item.getItemId());
                return true;
            case R.id.main_menu_3:
                label.setText(" 设置，菜单 ID：" + item.getItemId());
                return true;
            case R.id.main_menu_4:
                label.setText(" 订阅，菜单 ID：" + item.getItemId());
                return true;
            default:
                return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuexample);
    }
}
