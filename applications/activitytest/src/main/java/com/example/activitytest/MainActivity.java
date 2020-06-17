package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info info=new Info();
                if("".equals(((EditText)findViewById(R.id.birthday)).getText().toString())){
                    Toast.makeText(MainActivity.this,"请输入您的阳历生日，否则不能计算！",Toast.LENGTH_SHORT).show();
                    return;
                }
                String birthday=((EditText)findViewById(R.id.birthday)).getText().toString();
                info.setBirthday(birthday);
                Bundle bundle=new Bundle();
                bundle.putSerializable("info",info);
                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
