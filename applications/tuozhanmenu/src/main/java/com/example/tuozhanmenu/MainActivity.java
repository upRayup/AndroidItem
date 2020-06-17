package com.example.tuozhanmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        registerForContextMenu(tv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getGroupId() == R.id.setting) {
            switch (item.getItemId()) {
                case R.id.color1:
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    break;
                case R.id.color2:
                    tv.setTextColor(Color.rgb(0, 255, 0));
                    break;
                case R.id.color3:
                    tv.setTextColor(Color.rgb(0, 0, 255));
                    break;
                case R.id.color4:
                    tv.setTextColor(Color.rgb(255, 180, 0));
                    break;
                default:
                    break;
            }
        } else if (item.getGroupId() == R.id.setting2) {
            if (item.getItemId() == R.id.other1) {
                tv.setTextColor(Color.rgb(118, 146, 60));
            } else if (item.getItemId() == R.id.other2) {
                tv.setTextColor(Color.rgb(0, 255, 255));
            }
        }
        else {
            tv.setTextColor(Color.rgb(255, 255, 255));
        }
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}