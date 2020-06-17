package com.example.beiwanglu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    //定义对象
    EditText edit_inputname,edit_inputpwd;
    CheckBox check_reme;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //1 绑定控件
        initView();
        //2 单击登录按钮，将用户名和密码保存起来
        btnloginonClick();
        //3 下次启动，直接显示用户名和密码
        displayinfo();
    }
    //1 绑定控件--------代码
    private void initView() {
        edit_inputname=findViewById(R.id.editText_inputname);
        edit_inputpwd=findViewById(R.id.editText_inputpwd);
        check_reme=findViewById(R.id.checkBox_reme);
        btn_login=findViewById(R.id.button_login);
    }
    //2 单击登录按钮，将用户名和密码保存起来----代码
    private void btnloginonClick() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存用户名和密码
                SharedPreferences.Editor
                        editor=getSharedPreferences("myinfo",0).edit();
                editor.putString("name",edit_inputname.getText().toString());
                editor.putString("pwd",edit_inputpwd.getText().toString());
                editor.putBoolean("st",check_reme.isChecked());
                editor.commit();
                //跳转到第二页
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //3 下次启动，直接显示用户名和密码-----代码
    private void displayinfo() {
        String strname=getSharedPreferences("myinfo",0).getString("name","");
        String strpwd=getSharedPreferences("myinfo",0).getString("pwd","");
        Boolean status=getSharedPreferences("myinfo",0).getBoolean("st",false);
        if(status==true){
            edit_inputname.setText(strname);
            edit_inputpwd.setText(strpwd);
            check_reme.setChecked(true);
        }else{
            edit_inputname.setText("");
            edit_inputpwd.setText("");
            check_reme.setChecked(false);
        }
    } }